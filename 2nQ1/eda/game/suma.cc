sumax = unit(dw).pos.i - aux.i;
sumay = unit(dw).pos.j - aux.j;

if(sumax>0){
  if(sumay>0) command(dw, TL);
  else if(sumay<0) command(dw, RT);
  else command(dw, Top);
}

else if(sumax<0){
  if(sumay>0) command(dw, LB);
  else if(sumay<0) command(dw, BR);
  else command(dw, Bottom);
}

else if(sumax==0){
  if(sumay<0) command(dw, Left);
  if(sumay>0) command(dw, Right);
}
