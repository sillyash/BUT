.CODE

	pgcd	PROC
		
				MOV		EAX,EDX

		boucle:	MOV		EDX,0

				; (EDX:EAX) / ECX
				; on concatene EDX et EAX puis on divise par ECX
				; EDX = 0 donc on fait EAX / ECX
				; le resuktat est stocke dans EAX
				DIV		ECX

				; on remplace EAX par ECX pour préparer 
				; la prochaine division (ou le return)
				MOV		EAX,ECX
				MOV		ECX,EDX		; le reste de la division est dans EDX

				CMP		EDX,0		; on regarde si le reste est 0
				JNE		boucle		; s'il n'est pas nul on boucle

				RET

	pgcd	ENDP

END