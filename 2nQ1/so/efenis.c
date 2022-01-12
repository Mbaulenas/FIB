#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>

void help(){
    char buf[50];
    int pod = getpid();
    sprintf(buf,"%d: USR1 \n ", pod);
    write(1,buf, 50);
    exit(0);
}



int main(int agrc, char *argv[]){
    int n = atoi(argv[1]);
    int pid = -1;
    int *punt;
    char buf[50];

    sigset_t mask;
    struct sigaction sa;


    sigfillset(&mask);
    sigdelset(&mask, SIGUSR1);
    sigprocmask(SIG_BLOCK, &mask, NULL);

    sa.sa_handler = help;
    sa.sa_flags = 0;
    sigaction(SIGUSR1, &sa, NULL);

    punt = sbrk(n*sizeof(int));
    for(int i = 0; i < n && pid != 0; i++){
        pid = fork();
        if(pid != 0){
            punt[i] = pid;
        }
        else{
            int aux = getpid();
            sprintf(buf,"Soc el fill %d i el meu pid es %d \n", i , aux);
            write(1,buf,50);
            sigsuspend(&mask);
        }
    }
    read(0,buf,sizeof(char));
    while(buf[0] != '\n'){
        read(0,buf,sizeof(char));
    }
    for(int i = 0; i < n; i++){
        kill(punt[i],SIGUSR1);
    }
    exit(1);
}
