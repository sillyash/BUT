.DATA

	a	QWORD	?
	b	QWORD	?

.CODE

	ppcm	PROC

			MOV		a,CX
			MOV		b,DX

			MOV		AX,CX
			MOV		BX,DX

		boucleEXT:
			CMP		AX,BX
			JE		fin

		boucleInt1:
			CMP		AX,BX
			JAE		boucleInt2
			ADD		AX,CX
			JMP		BoucleInt1

		boucleInt2:
			CMP		AX,BX


	ppcm	ENDP

END