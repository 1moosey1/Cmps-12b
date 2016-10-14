# Makefile
# Make targets:
# make, make cyoa, make all, make clean, make test

MAINCLASS = TextAdventure
SOURCES = TextAdventure.java Room.java
CLASSES = TextAdventure.class Room.class \
    Room\$$Option.class
JARFILE = cyoa

all: ${JARFILE}
	
${JARFILE}: ${CLASSES}
	echo "Main-class: ${MAINCLASS}" > Manifest
	jar cvfm ${JARFILE} Manifest ${CLASSES}
	chmod +x ${JARFILE}
	rm Manifest
	
${CLASSES}: ${SOURCES}
	javac -Xlint ${SOURCES}
	
test: ${JARFILE}
	java -jar cyoa demo.adventure
	
clean:
	rm -f ${JARFILE} ${CLASSES}
	
.PHONY: all clean test