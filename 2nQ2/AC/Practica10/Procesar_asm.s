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

	movl 8(%ebp), %eax	#%eax -> mata
	movl 12(%ebp), %ebx	#ebx -> matb
	movl 16(%ebp), %ecx	#
	imul %ecx, %ecx		#%ecx = n*n
	addl %eax, %ecx		#%ecx = posicio final mata

for:	cmpl %eax,%ecx
	jle endfor
	movb (%eax),%dh
	salb $4,%dh		#d1*16
	movb %dh, (%ebx)
	incl %eax
	incl %ebx
	jmp for

endfor:

# El final de la rutina ya esta programado

	popl	%edi
	popl	%esi
	popl	%ebx
	movl %ebp,%esp
	popl %ebp
	ret
