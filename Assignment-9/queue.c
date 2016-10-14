// queue.c
// Implementation of the method declarations in queue.h
// Also contains private struct queue_node to be used in struct queue

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "queue.h"

#define STUBPRINTF(...) fprintf(stderr, __VA_ARGS__);

/* Internal implementation definitions */
struct queue_node {
   queue_item_t item;
   struct queue_node *link;
};

typedef struct queue_node queue_node;

struct queue {
   queue_node *front;
   queue_node *rear;
};

/* Functions */

queue * queue_new(void) {
   
   queue * newQueue = malloc(sizeof(queue));
   return newQueue;
}

void queue_free(queue *this) {
   assert(queue_isempty(this));
   free(this);
}

void queue_insert(queue *this, queue_item_t item) {
    
    queue_node * newNode = malloc(sizeof(queue_node));
    newNode->item = item;
    
    if(queue_isempty(this)) {
        
        this->front = newNode;
        this->rear = newNode;
    }
    else {
        
        this->rear->link = newNode;
        this->rear = newNode;
    }
}

queue_item_t queue_remove(queue *this) {
   assert(!queue_isempty(this));
   
   queue_node * deadNode = this->front;
   queue_item_t returnItem = this->front->item;
   
   this->front = deadNode->link;
   free(deadNode);
   
   return returnItem;
}

bool queue_isempty(queue *this) {
   return this->front == NULL;
}
