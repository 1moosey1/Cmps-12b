# File: Makefile
# This make file comes with four different options
# Option 1: make clean will remove all object files
# Option 2: make spotless will remove all object files and executables
# Option 3: make test will compile your c code and run it's unit tests
# Option 4: make queue will compile your c code and produce a runnable 

CC = gcc
CFLAGS = -std=c99 -Wall -Werror -Wfatal-errors -g
OBJS = queue.o
OUTPUT = queue
HEADERS = minunit.h

${OUTPUT}: ${OBJS}
	${CC} $< -o ${OUTPUT}

%.o: %.c ${HEADERS}
	${CC} -c ${CFLAGS} $<

clean:
	rm -f *.o

spotless: clean
	rm -f ${OUTPUT}

test: ${OUTPUT}
	./${OUTPUT}

.PHONY: clean spotless test

