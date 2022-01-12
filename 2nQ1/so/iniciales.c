#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main(int argc, char *argv[]){

  char *nom = argv[1];
  char buffer[128];
  char buffer2[128];

  int fd, fdl, n;
  sprintf(buffer,"%siniciales.txt",nom);
  fd = open(buffer, O_RDWR|O_CREAT|O_TRUNC,  S_IRWXU);
  fdl = open(argv[1], S_IRUSR);



  while( (n=read(fdl, buffer2, 1)) >0){

     write(fd, buffer2, 1);
     while(buffer2[0]!=' ' && n!=0) n=read(fdl, buffer2, 1);

  }

  close(fd);
  close(fdl);


}
