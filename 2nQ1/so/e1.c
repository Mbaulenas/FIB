#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <signal.h>


char buffer[128];
char tusmuertos[128];
void trat(){

  sprintf(tusmuertos, "%d : USR1 recibido.\n", getpid());
	write(1, tusmuertos, sizeof(tusmuertos));
  exit(0);
}

int main(int argc, char* argv[]){


  int ret;


  sigset_t mask;

  int i = atoi(argv[1]);
  int contador = 0;
  int* pids = malloc(1000*sizeof(int));

  //bloquejo tot menys usr1
  sigfillset(&mask);
  sigprocmask(SIG_BLOCK, &mask, NULL);

  while(ret!=0 && contador<i){

    ret = fork();

    if(ret==0){
      pids[contador] = getpid();
      sprintf(buffer, "Soc el fill %d i tinc PID: %d \n", contador, getpid());
      write(1, buffer, sizeof(buffer));
    }
    ++contador;
  }

  if(ret == 0){
    sigdelset(&mask, SIGUSR1);
    struct sigaction sa;
    sa.sa_handler = trat;
  	sa.sa_flags = 0;
    sa.sa_mask=mask;
    sigaction(SIGUSR1, &sa, NULL);
    sigsuspend(&mask);
  }

  else{
    char c[1];

    sprintf(buffer, "Los hijos estan ready boy, pulsa <ENTER>\n");
    write(1, buffer, sizeof(buffer));

    while(read(0, c, 1)<1 && c[0] !='\n');


    for(int j = 0; j<i; ++j){
      kill(pids[i], SIGUSR1);
    }


  }



}
