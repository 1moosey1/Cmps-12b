# Makefile
# Makefile for assignment 6
# Useable commands:
# make, make list, make test, make clean, make spotless

EXE = list
FLAGS = -c -Wall -g -std=c99
FILE = list.c
OBJ = list.o

${EXE}: ${OBJ}
	gcc ${OBJ} -o ${EXE}

${OBJ}: ${FILE}
	gcc ${FLAGS} ${FILE}

clean:
	rm -f ${OBJ}

spotless: clean
	rm -f ${EXE}

test: ${EXE}
	valgrind ./${EXE}

.PHONY: clean spotless test

