default rel

section .rodata
msg: db "One for you, one for me.", 0

section .text
global two_fer
two_fer:
    lea rax, [msg]
    ret
