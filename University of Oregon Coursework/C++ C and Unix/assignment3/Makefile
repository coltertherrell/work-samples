SOURCES = maze.c
OBJECTS = $(SOURCES:.c=.o)
LIBS = -lm
CC = gcc
CFLAGS = -std=c99 -g

%.o: %.c
	$(CC) -c $(CFLAGS) $<

maze: $(OBJECTS)
	$(CC) -o $@ $(OBJECTS)

clean: 
	$(RM) maze $(OBJECTS)
