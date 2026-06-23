main    START   0
        EXTDEF	array
        EXTREF	sum
        EXTREF	print

        LDA     #3
        LDB     #5
        JSUB	init
        +JSUB	sum
        +JSUB	print

H	J       H

init    STCH    array, X
        ADD     #2
        TIXR	B
        JLT     init
        CLEAR	X
        CLEAR	A
        RSUB

array   RESB    100

sum     CSECT
        EXTREF	array
loop    +LDCH   array, X
        ADDR    A, S
        TIXR    B
        JLT     loop
        RMO     S, A
        CLEAR   S
        CLEAR   X
        RSUB
