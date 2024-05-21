.CODE

	factorielle		PROC

				MOV		RBX,1
				MOV		RAX,1
				INC		RCX

		boucle:	MUL		RBX
				INC		RBX
				CMP		RCX,RBX
				JE		return
				JMP		boucle
		
		return:	RET

	factorielle		ENDP

END