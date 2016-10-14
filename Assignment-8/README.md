# CMPS12B-Assignment8

<h1> Objective </h1>

<p>
The purpose of this assignment is to give you practice implementing binary trees and queues in Java,
and also in combining different data structures together in order to solve more interesting problems.
</p>

<h1> Task </h1>

<p>
Your task is to write a utility that indexes words in a file. Your program should scan a file looking for
words, then print out a table of all words found in lexicographic order together with a list of line
numbers indicating where the word was found.

The program will use a binary tree data structure. Words must be able to be inserted and found in the
tree in O(log n) time. No balancing of the tree needs to be performed, that is beyond the scope of this
class. The value at each tree node will be a queue of line numbers. You must implement these queues
using linked lists so that they do not run out of space and each operation runs in O(1) time.
</p>
