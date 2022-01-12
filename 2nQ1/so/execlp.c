#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <signal.h>


int main(int argc, char* argv[]){


  execlp("./e1.x","e1.x","3",NULL);


}
