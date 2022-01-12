#define _GNU_SOURCE             /* See feature_test_macros(7) */
#include <fcntl.h>              /* Obtain O_* constant definitions */
#include <unistd.h>


int main(int argc, char *argv[]){

  int pipas[2];
  pipe(pipas);
  char *nom = argv[1];
  dup2(nom ,pipas[0]);

  int ret = fork();

  if(ret==0){

    dup2(pipas[0], 0);
    close(pipas[0]); close(pipas[1]);
    execlp("wc","wc","-c",(char *)0);
  }

}
