/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.pos.electronic;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.SerializableRead;
import com.unicenta.pos.ticket.TicketInfo;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 * @author Pablo Porras
 */
public final class ElectronicBill implements SerializableRead, Externalizable {

    private String m_sId;
    private int status;
    private String xmlInfo;
    private String numElectronicBill;

    public String getNumElectronicBill() {
        return numElectronicBill;
    }

    public void setNumElectronicBill(String numElectronicBill) {
        this.numElectronicBill = numElectronicBill;
    }

    public String getM_sId() {
        return m_sId;
    }

    public void setM_sId(String m_sId) {
        this.m_sId = m_sId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getXmlInfo() {
        return xmlInfo;
    }

    public void setXmlInfo(String xmlInfo) {
        this.xmlInfo = xmlInfo;
    }
    
    public ElectronicBill(TicketInfo ticket) {
        this.m_sId = ticket.getId();
        this.status = 1;
        this.generateXML(ticket);
    }

    private void generateXML(TicketInfo ticket) {

        String str = "<FACTURA>\n"
                + "	<ENC>\n"
                + "		<ENC_1>INVOIC</ENC_1>\n"
                + "		<ENC_2>901143311</ENC_2>\n"
                + "		<ENC_3>805009741</ENC_3>\n"
                + "		<ENC_4>UBL 2.1</ENC_4>\n"
                + "		<ENC_5>DIAN 2.1</ENC_5>\n"
                + "		<ENC_6>SETT152</ENC_6>\n"
                + "		<ENC_9>01</ENC_9>\n"
                + "		<ENC_10>COP</ENC_10>\n"
                + "		<ENC_15>1</ENC_15>\n"
                + "		<ENC_16>2024-05-03</ENC_16>\n"
                + "		<ENC_20>2</ENC_20>\n"
                + "		<ENC_21>10</ENC_21>\n"
                + "	</ENC>\n"
                + "	<IPV>\n"
                + "		<IPV_1>PLCINV123</IPV_1> \n"
                + "		<IPV_2>Zona Centro</IPV_2> \n"
                + "		<IPV_3>Alan Farquet</IPV_3> \n"
                + "		<IPV_4>TC01</IPV_4> \n"
                + "		<IPV_5>CV01</IPV_5> \n"
                + "		<IPV_6>1884000</IPV_6> \n"
                + "	</IPV> \n"
                + "	<BAC>\n"
                + "		<BAC_1>CV001</BAC_1>\n"
                + "		<BAC_2>Alan Farquet</BAC_2>\n"
                + "		<BAC_3>1000</BAC_3>\n"
                + "	</BAC>\n"
                + "	<EMI>\n"
                + "		<EMI_1>1</EMI_1>\n"
                + "		<EMI_2>901143311</EMI_2>\n"
                + "		<EMI_3>31</EMI_3>\n"
                + "		<EMI_6>Alan Jair Guevara Farquet</EMI_6>\n"
                + "		<EMI_7>Alan Jair Guevara Farquet</EMI_7>\n"
                + "		<EMI_10>CALLE 11 oriente 1216</EMI_10>\n"
                + "		<EMI_11>08</EMI_11>\n"
                + "		<EMI_13>BARRANQUILLA</EMI_13>\n"
                + "		<EMI_14>08001</EMI_14>\n"
                + "		<EMI_15>CO</EMI_15>\n"
                + "		<EMI_19>ATLANTICO</EMI_19>\n"
                + "		<EMI_22>8</EMI_22>\n"
                + "		<EMI_23>08001</EMI_23>\n"
                + "		<EMI_24>Alan Jair Guevara Farquet</EMI_24>\n"
                + "		<TAC>\n"
                + "			<TAC_1>R-99-PN</TAC_1>\n"
                + "		</TAC>\n"
                + "		<DFE>\n"
                + "			<DFE_1>08001</DFE_1>\n"
                + "			<DFE_2>08</DFE_2>\n"
                + "			<DFE_3>CO</DFE_3>\n"
                + "			<DFE_4>08001</DFE_4>\n"
                + "			<DFE_5>Colombia</DFE_5>\n"
                + "			<DFE_6>ATLANTICO</DFE_6>\n"
                + "			<DFE_7>BARRANQUILLA</DFE_7>\n"
                + "			<DFE_8>CR CORREDOR UNIVERSITARIO 30 1 850 CS 416</DFE_8>\n"
                + "		</DFE>\n"
                + "		<ICC>\n"
                + "			<ICC_1>1</ICC_1>\n"
                + "			<ICC_9>SETT</ICC_9>\n"
                + "		</ICC>\n"
                + "		<CDE>			\n"
                + "			<CDE_2>PATRICIA ALTAHONA JIMENEZ- CAJERA</CDE_2>\n"
                + "			<CDE_3>5731129293061</CDE_3>\n"
                + "			<CDE_4>afarque@outlook.com</CDE_4>			\n"
                + "		</CDE>\n"
                + "		<GTE>\n"
                + "			<GTE_1>01</GTE_1>\n"
                + "			<GTE_2>IVA</GTE_2>\n"
                + "		</GTE>\n"
                + "	</EMI>\n"
                + "	<ADQ>\n"
                + "		<ADQ_1>1</ADQ_1>\n"
                + "		<ADQ_2>805009741</ADQ_2>\n"
                + "		<ADQ_3>22</ADQ_3>\n"
                + "		<ADQ_6>Jose Luis Solar Avendaño</ADQ_6>\n"
                + "		<ADQ_11>08</ADQ_11>\n"
                + "		<ADQ_14>080001</ADQ_14>\n"
                + "		<ADQ_15>CO</ADQ_15>\n"
                + "		<TCR>\n"
                + "			<TCR_1>R-99-PN</TCR_1>\n"
                + "		</TCR>\n"
                + "		<CDA>\n"
                + "			<CDA_2>JORGE W PEMBERTY</CDA_2>\n"
                + "            <CDA_3>22222222226</CDA_3>\n"
                + "			<CDA_4>afarque@gmail.com</CDA_4>\n"
                + "		</CDA>\n"
                + "		<GTA>\n"
                + "			<GTA_1>01</GTA_1>\n"
                + "			<GTA_2>IVA</GTA_2>\n"
                + "		</GTA>\n"
                + "	</ADQ>\n"
                + "    <TOT>\n"
                + "		<TOT_1>1884000.00</TOT_1>\n"
                + "		<TOT_2>COP</TOT_2>\n"
                + "		<TOT_3>0.00</TOT_3>\n"
                + "		<TOT_4>COP</TOT_4>\n"
                + "		<TOT_5>1884000.00</TOT_5>\n"
                + "		<TOT_6>COP</TOT_6>\n"
                + "		<TOT_7>1884000.00</TOT_7>\n"
                + "		<TOT_8>COP</TOT_8>\n"
                + "	</TOT>\n"
                + "	<DRF>  <!-- Para pruebas debera colocar la informacion del SETT ID de habilitacion de facturador electronico --> \n"
                + "		<DRF_1>18760000001</DRF_1>\n"
                + "		<DRF_2>2019-01-19</DRF_2>\n"
                + "		<DRF_3>2030-01-19</DRF_3>\n"
                + "		<DRF_4>SETT</DRF_4>\n"
                + "		<DRF_5>1</DRF_5>\n"
                + "		<DRF_6>5000000</DRF_6>\n"
                + "	</DRF>\n"
                + "	<MEP>\n"
                + "		<MEP_1>1</MEP_1>\n"
                + "		<MEP_2>1</MEP_2>\n"
                + "	</MEP>\n"
                + "	<ITE>\n"
                + "		<ITE_1>1</ITE_1>\n"
                + "		<ITE_3>24.00</ITE_3>\n"
                + "		<ITE_4>94</ITE_4>\n"
                + "		<ITE_5>1884000.00</ITE_5>\n"
                + "		<ITE_6>COP</ITE_6>\n"
                + "		<ITE_7>78500.00</ITE_7>\n"
                + "		<ITE_8>COP</ITE_8>\n"
                + "		<ITE_11>CONSULTA DE PRIMERA VEZ POR ESPECIALISTA EN GINECOLOGÍA Y OBSTETRICIA</ITE_11>\n"
                + "		<ITE_19>1884000.00</ITE_19>\n"
                + "		<ITE_20>COP</ITE_20>\n"
                + "		<ITE_27>24</ITE_27>\n"
                + "		<ITE_28>94</ITE_28>\n"
                + "		<IAE>\n"
                + "			<IAE_1>85101503</IAE_1>\n"
                + "			<IAE_2>001</IAE_2>\n"
                + "		</IAE>\n"
                + "	</ITE>\n"
                + "</FACTURA>";

        this.xmlInfo = str;
    }

    private void getNumFactura(){
        if(numElectronicBill == null){
            
        }
    }
    
    @Override
    public void readValues(DataRead dr) throws BasicException {
        m_sId = dr.getString(1);
        status = dr.getInt(2);
        xmlInfo = dr.getString(3);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(m_sId);
        out.writeInt(status);
        out.writeObject(xmlInfo);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        m_sId = (String) in.readObject();
        status = in.readInt();
        xmlInfo = (String) in.readObject();
    }

}
