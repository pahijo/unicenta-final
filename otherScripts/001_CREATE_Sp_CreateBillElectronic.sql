CREATE DEFINER=`root`@`%` PROCEDURE `unicentaopos`.`Sp_CreateBillElectronic`(numerTicket int)
BEGIN
	
	DECLARE TOTAL VARCHAR(100); 
	DECLARE PAGADO VARCHAR(100);
	DECLARE RATE VARCHAR(100);
	DECLARE COD_IMPUESTO VARCHAR(100);
	DECLARE BASE_IMPUESTO VARCHAR(100);
	DECLARE VR_IMPUESTO VARCHAR(100);

	DECLARE ENC TEXT;
	DECLARE ENC_1 VARCHAR(100);
	DECLARE ENC_2  VARCHAR(100);
	DECLARE ENC_4  VARCHAR(100);  	
	DECLARE ENC_5  VARCHAR(100);
	DECLARE ENC_6  VARCHAR(100);
	DECLARE ENC_9  VARCHAR(100);
	DECLARE ENC_10 VARCHAR(100);
	DECLARE ENC_15 VARCHAR(100);
	DECLARE ENC_20 VARCHAR(100);
	DECLARE ENC_21 VARCHAR(100);

	DECLARE IPV TEXT;
	DECLARE IPV_1   VARCHAR(100);
	DECLARE IPV_2   VARCHAR(100);
	DECLARE IPV_3  VARCHAR(100);
	DECLARE IPV_4	VARCHAR(100);
	DECLARE IPV_5	VARCHAR(100);
	
	DECLARE EMI TEXT;
	DECLARE EMI_1 VARCHAR(100);
	DECLARE EMI_2 VARCHAR(100);
	DECLARE EMI_3 VARCHAR(100);
	DECLARE EMI_6 VARCHAR(100);
	DECLARE EMI_7 VARCHAR(100);
	DECLARE EMI_10 VARCHAR(100);
	DECLARE EMI_11 VARCHAR(100);

	DECLARE TAC TEXT;
	DECLARE TAC_1 VARCHAR(100);

	DECLARE DFE TEXT;
	DECLARE DFE_1 VARCHAR(100);
	DECLARE DFE_2 VARCHAR(100);
	DECLARE DFE_3 VARCHAR(100);
	DECLARE DFE_4 VARCHAR(100);
	DECLARE DFE_5 VARCHAR(100);
	DECLARE DFE_6 VARCHAR(100);
	DECLARE DFE_7 VARCHAR(100);
	DECLARE DFE_8 VARCHAR(100);

	DECLARE ICC TEXT;
	DECLARE ICC_1 VARCHAR(100);
	DECLARE ICC_9 VARCHAR(100);

	DECLARE CDE TEXT;
	DECLARE CDE_2 VARCHAR(100);

	DECLARE GTE TEXT;
	DECLARE GTE_1 VARCHAR(100);
	DECLARE GTE_2 VARCHAR(100);



	SELECT  value INTO ENC_1 FROM fe_parameters WHERE CODE = 'ENC_1';
	SELECT  value INTO ENC_2 FROM fe_parameters WHERE CODE = 'ENC_2';
	SELECT  value INTO ENC_4 FROM fe_parameters WHERE CODE = 'ENC_4';
	SELECT  value INTO ENC_5 FROM fe_parameters WHERE CODE = 'ENC_5';
	SELECT  value INTO ENC_6 FROM fe_parameters WHERE CODE = 'ENC_6';
	SELECT  value INTO ENC_9 FROM fe_parameters WHERE CODE = 'ENC_9';
	SELECT  value INTO ENC_10 FROM fe_parameters WHERE CODE = 'ENC_10';
	SELECT  value INTO ENC_15 FROM fe_parameters WHERE CODE = 'ENC_15';
	SELECT  value INTO ENC_20 FROM fe_parameters WHERE CODE = 'ENC_20';
	SELECT  value INTO ENC_21 FROM fe_parameters WHERE CODE = 'ENC_21';

	SELECT  value INTO IPV_1 FROM fe_parameters WHERE CODE = 'IPV_1';
	SELECT  value INTO IPV_2 FROM fe_parameters WHERE CODE = 'IPV_2';
	SELECT  value INTO IPV_3 FROM fe_parameters WHERE CODE = 'IPV_3';
	SELECT  value INTO IPV_4 FROM fe_parameters WHERE CODE = 'IPV_4';
	SELECT  value INTO IPV_5 FROM fe_parameters WHERE CODE = 'IPV_5';

	SELECT  value INTO EMI_1 FROM fe_parameters WHERE CODE = 'EMI_1';
	SELECT  value INTO EMI_2 FROM fe_parameters WHERE CODE = 'EMI_2';
	SELECT  value INTO EMI_3 FROM fe_parameters WHERE CODE = 'EMI_3';
	SELECT  value INTO EMI_6 FROM fe_parameters WHERE CODE = 'EMI_6';
	SELECT  value INTO EMI_7 FROM fe_parameters WHERE CODE = 'EMI_7';
	SELECT  value INTO EMI_10 FROM fe_parameters WHERE CODE = 'EMI_10';
	SELECT  value INTO EMI_11 FROM fe_parameters WHERE CODE = 'EMI_11';

	SELECT  value INTO TAC_1 FROM fe_parameters WHERE CODE = 'TAC_1';

	SELECT  value INTO DFE_1 FROM fe_parameters WHERE CODE = 'DFE_1';
	SELECT  value INTO DFE_2 FROM fe_parameters WHERE CODE = 'DFE_2';
	SELECT  value INTO DFE_3 FROM fe_parameters WHERE CODE = 'DFE_3';
	SELECT  value INTO DFE_4 FROM fe_parameters WHERE CODE = 'DFE_4';
	SELECT  value INTO DFE_5 FROM fe_parameters WHERE CODE = 'DFE_5';
	SELECT  value INTO DFE_6 FROM fe_parameters WHERE CODE = 'DFE_6';
	SELECT  value INTO DFE_7 FROM fe_parameters WHERE CODE = 'DFE_7';
	SELECT  value INTO DFE_8 FROM fe_parameters WHERE CODE = 'DFE_8';

	SELECT  value INTO ICC_1 FROM fe_parameters WHERE CODE = 'ICC_1';
	SELECT  value INTO ICC_9 FROM fe_parameters WHERE CODE = 'ICC_9';

	SELECT  value INTO CDE_2 FROM fe_parameters WHERE CODE = 'CDE_2';

	SELECT  value INTO GTE_1 FROM fe_parameters WHERE CODE = 'GTE_1';
	SELECT  value INTO GTE_2 FROM fe_parameters WHERE CODE = 'GTE_2';
	
	
    /*Valores de la factura - ticket*/

	SELECT  
	/*Información del ticket o factura*/
	CAST(p.total AS CHAR),CAST(p.tendered  AS CHAR),CAST(x.rate  AS CHAR), CAST(xl.taxid  AS CHAR),CAST(ROUND(xl.base,2) AS CHAR) base,CAST(ROUND(xl.amount,2)  AS CHAR) amount 
	INTO TOTAL, PAGADO, RATE, COD_IMPUESTO , BASE_IMPUESTO, VR_IMPUESTO
	FROM unicentaopos.tickets t
	INNER JOIN unicentaopos.ticketlines l ON t.id = l.ticket 
	INNER JOIN unicentaopos.payments p ON t.id  = p.receipt 
	INNER JOIN unicentaopos.taxes x ON l.taxid = x.id 
	INNER JOIN unicentaopos.taxlines xl ON x.id = xl.taxid AND t.id = xl.receipt 
	INNER JOIN unicentaopos.products pd ON l.product = pd.id 
	INNER JOIN unicentaopos.people pp ON t.person = pp.id
	INNER JOIN unicentaopos.receipts r ON t.id = r.id 
	LEFT JOIN unicentaopos.customers c ON t.customer = c.id 
	WHERE t.ticketid = numerTicket
	GROUP BY p.total,p.tendered,x.rate, xl.taxid,xl.base,xl.amount;
	
	SET ENC = CONCAT('<FACTURA>
	<ENC>
		<ENC_1>' , CAST(ENC_1 AS CHAR) , '</ENC_1>
		<ENC_2>' , CAST(ENC_2 AS CHAR), '</ENC_2>
		<ENC_3>805009741</ENC_3>
		<ENC_4>' , CAST(ENC_4 AS CHAR), '</ENC_4>
		<ENC_5>' , CAST(ENC_5 AS CHAR), '</ENC_5>
		<ENC_6>' , CAST(ENC_6 AS CHAR), '</ENC_6>
		<ENC_9>' , CAST(ENC_9 AS CHAR), '</ENC_9>
		<ENC_10>' , CAST(ENC_10 AS CHAR), '</ENC_10>
		<ENC_15>' , CAST(ENC_15 AS CHAR), '</ENC_15>
		<ENC_16>2024-05-03</ENC_16>
		<ENC_20>' , CAST(ENC_20 AS CHAR), '</ENC_20>
		<ENC_21>' , CAST(ENC_21 AS CHAR), '</ENC_21>
	</ENC>');
	
	SET IPV = CONCAT('
		<IPV>
		<IPV_1>',CAST(IPV_1 AS CHAR),'</IPV_1> 
		<IPV_2>',CAST(IPV_2 AS CHAR),'</IPV_2> 
		<IPV_3>',CAST(IPV_3 AS CHAR),'</IPV_3> 
		<IPV_4>',CAST(IPV_4 AS CHAR),'</IPV_4> 
		<IPV_5>',CAST(IPV_5 AS CHAR),'</IPV_5> 
		<IPV_6>',BASE_IMPUESTO,'</IPV_6> 
	</IPV>'); 

	SET EMI =  CONCAT('<EMI>
		<EMI_1>',CAST(EMI_1 AS CHAR),'</EMI_1>
		<EMI_2>',CAST(EMI_2 AS CHAR),'</EMI_2>
		<EMI_3>',CAST(EMI_3 AS CHAR),'</EMI_3>
		<EMI_6>',CAST(EMI_6 AS CHAR),'</EMI_6>
		<EMI_7>',CAST(EMI_7 AS CHAR),'</EMI_7>
		<EMI_10>',CAST(EMI_10 AS CHAR),'</EMI_10>
		<EMI_11>',CAST(EMI_11 AS CHAR),'</EMI_11>');
	
	SET TAC = CONCAT('<TAC>
			<TAC_1>',CAST(TAC_1 AS CHAR),'</TAC_1>
		</TAC>');

	SET DFE = CONCAT('<DFE>
			<DFE_1>',CAST(DFE_1 AS CHAR),'</DFE_1>
			<DFE_2>',CAST(DFE_2 AS CHAR),'</DFE_2>
			<DFE_3>',CAST(DFE_3 AS CHAR),'</DFE_3>
			<DFE_4>',CAST(DFE_4 AS CHAR),'</DFE_4>
			<DFE_5>',CAST(DFE_5 AS CHAR),'</DFE_5>
			<DFE_6>',CAST(DFE_6 AS CHAR),'</DFE_6>
			<DFE_7>',CAST(DFE_7 AS CHAR),'</DFE_7>
			<DFE_8>',CAST(DFE_8 AS CHAR),'</DFE_8>
		</DFE>');
	
	/*----opcional------*/
	SET ICC = CONCAT('<ICC>
			<ICC_1>',CAST(ICC_1 AS CHAR),'</ICC_1>
			<ICC_9>',CAST(ICC_9 AS CHAR),'</ICC_9>
		</ICC>');
	
	
	SET CDE = CONCAT('<CDE>			
			<CDE_2>',CAST(ICC_1 AS CHAR),'</CDE_2>
		</CDE>');
	
	
	SET GTE = CONCAT('<GTE>
			<GTE_1>',CAST(GTE_1 AS CHAR),'</GTE_1>
			<GTE_2>',CAST(GTE_2 AS CHAR),'</GTE_2>
		</GTE>
		</EMI>');
	
	
	
	/*------Impresion de factura-------*/
	
	IF LENGTH (ICC_1) > 0 THEN
		SELECT CONCAT(ENC,IPV,EMI,TAC,DFE,ICC,CDE,GTE) as facturaXML;
	ELSE
		SELECT CONCAT(ENC,IPV,EMI,TAC,DFE,CDE,GTE) as facturaXML;
	END IF;
	


END