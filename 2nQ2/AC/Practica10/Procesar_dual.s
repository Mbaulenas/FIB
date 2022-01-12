.text
	.align 4
	.globl procesar
	.type	procesar, @function
procesar:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$16, %esp
	pushl	%ebx
	pushl	%esi
	pushl	%edi

# Aqui has de introducir el codigo

	movl 8(%ebp), %eax	#%eax -> mata
	movl 12(%ebp), %ebx	#ebx -> matb
	movl 16(%ebp), %ecx	#
	imul %ecx, %ecx		#%ecx = n*n
	addl %eax, %ecx		#%ecx = posicio final mata

	movl %eax, %edx		
  	andl $0xF, %edx
  	jne else		

alineat:

	cmp %eax, %ecx
	jle endfor

	movdqa (%eax), %xmm0
	paddb %xmm0, %xmm0
	paddb %xmm0, %xmm0
	paddb %xmm0, %xmm0
	paddb %xmm0, %xmm0

	movdqa %xmm0, (%ebx)
	addl $16, %eax
	addl $16, %ebx

	jmp alineat

else:
	
	cmp %eax, %ecx
	jle endfor

	movdqu (%eax), %xmm0
	paddb %xmm0, %xmm0
	paddb %xmm0, %xmm0
	paddb %xmm0, %xmm0
	paddb %xmm0, %xmm0

	movdqu %xmm0, (%ebx)
	addl $16, %eax
	addl $16, %ebx

	jmp else

endfor:


# El final de la rutina ya esta programado

	emms	# Instruccion necesaria si os equivocais y usais MMX
	popl	%edi
	popl	%esi
	popl	%ebx
	movl %ebp,%esp
	popl %ebp
	ret
