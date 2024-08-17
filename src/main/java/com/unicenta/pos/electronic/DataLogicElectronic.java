/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicenta.pos.electronic;

import com.unicenta.basic.BasicException;
import com.unicenta.data.loader.DataParams;
import com.unicenta.data.loader.DataRead;
import com.unicenta.data.loader.Datas;
import com.unicenta.data.loader.PreparedSentence;
import com.unicenta.data.loader.SentenceFind;
import com.unicenta.data.loader.SerializerReadDate;
import com.unicenta.data.loader.SerializerReadInteger;
import com.unicenta.data.loader.SerializerReadString;
import com.unicenta.data.loader.SerializerWriteParams;
import com.unicenta.data.loader.SerializerWriteString;
import com.unicenta.data.loader.Session;
import com.unicenta.data.loader.StaticSentence;
import com.unicenta.data.loader.Transaction;
import com.unicenta.pos.forms.AppConfig;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.forms.AppView;
import com.unicenta.pos.forms.BeanFactoryDataSingle;
import com.unicenta.pos.ticket.TicketInfo;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author Pablo Porras
 */
public class DataLogicElectronic extends BeanFactoryDataSingle {

    protected Datas[] electronicDatas;
    protected Session s;
    private static final Logger logger = Logger.getLogger("com.unicenta.pos.electronic.DataLogicElectronic");
    protected AppView m_App;
    private AppConfig m_config;

    public DataLogicElectronic() {
        AppView app = null;
        m_config = new AppConfig(new File((System.getProperty("user.home")), AppLocal.APP_ID + ".properties"));
        m_config.load();
        m_App = app;

        electronicDatas = new Datas[]{
            Datas.STRING, Datas.INT, Datas.STRING};

    }

    @Override
    public void init(Session s) {
        this.s = s;
    }

    public SentenceFind nextFactura(Session s){
        return new StaticSentence(s, "SELECT CASE  WHEN (coalesce(MAX(CAST(numEectronicBill as unsigned)),0) = 0)  THEN (SELECT coalesce(description,0) FROM unicentaopos.fe_parameters WHERE code = 'DRF _5')  ELSE (coalesce(MAX(CAST(numEectronicBill as unsigned)),0) + 1) END AS NUMFAC FROM unicentaopos.fe_electronic_bill"
                , null, SerializerReadInteger.INSTANCE);
    }

    private final PreparedSentence loadNextFactura() throws BasicException {
      var item   =  new PreparedSentence(s,
                "SELECT CASE  WHEN (coalesce(MAX(CAST(numEectronicBill as unsigned)),0) = 0)  THEN (SELECT coalesce(description,0) FROM unicentaopos.fe_parameters WHERE code = 'DRF _5')  ELSE (coalesce(MAX(CAST(numEectronicBill as unsigned)),0) + 1) END AS NUMFAC FROM unicentaopos.fe_electronic_bill",
                SerializerWriteString.INSTANCE,
               (DataRead dr) -> dr.getInt(1));
        
     /* var item   = new StaticSentence(s
            , "SELECT CASE  WHEN (coalesce(MAX(CAST(numEectronicBill as unsigned)),0) = 0)  THEN (SELECT coalesce(description,0) FROM unicentaopos.fe_parameters WHERE code = 'DRF _5')  ELSE (coalesce(MAX(CAST(numEectronicBill as unsigned)),0) + 1) END AS NUMFAC FROM unicentaopos.fe_electronic_bill"
            , SerializerWriteString.INSTANCE
            , SerializerReadDate.INSTANCE);*/
        return item;
    }
 
    public final void saveTicketBill(final TicketInfo ticket) throws BasicException {

        if (ticket.getId() != null) {
            Transaction t;
            t = new Transaction(s) {
                @Override
                public Object transact() throws BasicException {
                    
                    //consulta consecutivo 
                    ElectronicBill bill = new ElectronicBill(ticket);
                    var value = (Integer) nextFactura(s).find();
                    bill.setNumElectronicBill(value.toString());

                    
                    //new electronic
                        new PreparedSentence(s
                        , "INSERT INTO fe_electronic_bill (ID, status, xmlInfo, numEectronicBill) "
                        + "VALUES (?, ?, ?, ?)"
                        , SerializerWriteParams.INSTANCE )
                        .exec(new DataParams() {

                            @Override
                            public void writeValues() throws BasicException {
                                setString(1, bill.getM_sId());
                                setInt(2, bill.getStatus());
                                setString(3, bill.getXmlInfo());
                                setString(4, bill.getNumElectronicBill());
                            }
                        });
                         return null;
                }
            };
            t.execute();
        }
    }
}

