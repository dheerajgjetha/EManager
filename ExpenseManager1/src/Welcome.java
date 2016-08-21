
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import org.bson.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author djetha
 */

public class Welcome extends javax.swing.JFrame {

    String unm;
    
    /**
     * Creates new form Welcome
     */
    public Welcome() {
        initComponents();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("Add Account");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Remove Account");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Welcome @ Expense Manager");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(407, 407, 407)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setData(String username)
    {
        this.unm=username;
        
    }
     public void setListVal()
    {
         int i=1;
        MongoClient client=new MongoClient("localhost", 27017);
          MongoDatabase db = client.getDatabase("ExpenseManager");
        MongoCollection<Document> reg = db.getCollection("Registration");
        FindIterable<Document> find = reg.find(new Document("unm", unm));
        
        Document doc=find.first();
       
        DefaultListModel model;
          model=new DefaultListModel();
          while(i<=5)
          {
            if(!doc.containsKey("Account"+i))
            {
                i++;
            }
            else
            {
                while (doc.containsKey("Account" + i)) {
                    String s=(String)doc.get("Account"+i);
                    s=s.concat("              ");
                    s=s.concat((String)doc.get("Bank"+i));
                    s=s.concat("              ");
                    s=s.concat((String)doc.get("Money"+i));
                    model.addElement(s);
                    i++;
                }
            }
        }
          jList1.setModel(model);
    }
        
     public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
    jLabel1.setText("Welcome "+unm);
    
    // TODO add your handling code here:
    }//GEN-LAST:event_formMouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String accno = JOptionPane.showInputDialog(this, "Enter Your Account Number");
        if (isInteger(accno)) {
            
            int value = JOptionPane.showConfirmDialog(this, "Do You Want to add Account No. " + accno);
            if (value == 0) {
                String Banks[]={
        "",            
        "Allahabad Bank",
        "Andhra Bank",
        "Bank of Baroda",
        "Bank of India",
        "Bank of Maharashtra",
        "Canara Bank",
        "Central Bank of India",
        "Corporation Bank",
        "Dena Bank",
        "Indian Bank",
        "Indian Overseas Bank",
        "Oriental Bank of Commerce",
        "Punjab & Sind Bank",
        "Punjab National Bank",
        "Syndicate Bank",
        "UCO Bank",
        "Union Bank of India",
        "United Bank of India",
        "Vijaya Bank",
        "Axis Bank",
        "City Union Bank",
        "Dhanlaxmi Bank",
        "Federal Bank",
        "HDFC Bank",
        "ICICI Bank",
        "IDFC Bank",
        "Karnataka Bank",
        "IndusInd Bank",
        "ING Vysya Bank", 
        "Jammu and Kashmir Bank",
        "Karur Vysya Bank",
        "Kotak Mahindra Bank",
        "Yes bank",
        "Citi Bank",
        "State Bank of India", 
        "State Bank of Patiala",
        "State Bank of Mysore", 
        "State Bank of Travancore", 
        "State Bank of Bikaner and Jaipur",     
        "State Bank of Hyderabad", 
        "State Bank Of Saurashtra"
                
            };
                Arrays.sort(Banks);
                JComboBox jcb = new JComboBox(Banks);
                jcb.setEditable(true);
                JOptionPane.showMessageDialog( this, jcb, "Select Your Bank", JOptionPane.QUESTION_MESSAGE);
                while(jcb.getSelectedItem().equals(""))
                {
                    JOptionPane.showMessageDialog(this, "Please Select the Bank");
                     JOptionPane.showMessageDialog( this, jcb, "Select Your Bank", JOptionPane.QUESTION_MESSAGE);
                }
                String money=JOptionPane.showInputDialog(this,"Enter the amount present in "+accno+" account");
                String Bank=(String) jcb.getSelectedItem();
                while(!isInteger(money))
                {
                    JOptionPane.showMessageDialog(this, "You entered a Wrong Value. Please Enter Correct Value");
                     money=JOptionPane.showInputDialog(this,"Enter the amount present in "+accno+" account");
                }
                MongoClient client = new MongoClient("localhost", 27017);
                DB db;
                db = client.getDB("ExpenseManager");
                DBCollection reg = (DBCollection) db.getCollection("Registration");
                DBObject query = new BasicDBObject("unm", unm);
                DBObject update = new BasicDBObject();
                

                DBCursor find = reg.find(query);
                while (find.hasNext()) {
                    DBObject next = find.next();
                    int count = 1;
                    for (int i = 1; i <= 5; i++) {
                        String str1="Account"+i;
                        String str2="Bank"+i;
                        String str3="Money"+i;
                        System.out.println(str1);
                        if (!(next.containsField(str1))) {
                            update.put("$set",new BasicDBObject(str1, accno).append(str2, Bank).append(str3, money));
                            WriteResult result=reg.update(query, update);
                            setListVal();
                           break;
                        }
                    }
                }

            }
           
        } else {
            JOptionPane.showMessageDialog(this, "You Entered a wrong Number");
        }















        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         /*MongoClient client = new MongoClient("localhost", 27017);
                DB db;
                db = client.getDB("ExpenseManager");
                DBCollection reg = (DBCollection) db.getCollection("Registration");
                DBObject query = new BasicDBObject("unm", unm);
                DBObject update = new BasicDBObject();
                
                update.put("$unset", new BasicDBObject("userId",""));
*/
                 int i=1;
                 int count=1;
        MongoClient client=new MongoClient("localhost", 27017);
          MongoDatabase db = client.getDatabase("ExpenseManager");
        MongoCollection<Document> reg = db.getCollection("Registration");
        FindIterable<Document> find = reg.find(new Document("unm", unm));
        DBObject query=new BasicDBObject("unm", unm);
        BasicDBObject update=new BasicDBObject();
        Map<Integer,Integer> m=new HashMap<Integer,Integer>();
        String val[]=new String[6];
        val[0]="";
        Document doc=find.first();
         while(i<=5)
          {
            if(!doc.containsKey("Account"+i))
            {
                i++;
            }
            else
            {
                while (doc.containsKey("Account" + i)) {
                    m.put(count,i);
                    val[count]=(String) (doc.get("Account" + i));
                    i++;
                    count++;
                }
            }
        }
                JComboBox jcb = new JComboBox(val);
                jcb.setEditable(true);
        int msg = JOptionPane.showConfirmDialog(this, "Do You Really Want to remove Your Account?");
               if(msg==0)
               {
                    JOptionPane.showMessageDialog( this, jcb, "Select Your Account to Remove", JOptionPane.QUESTION_MESSAGE);
                    
                    while(jcb.getSelectedItem().equals(""))
                    {
                        JOptionPane.showMessageDialog(this, "You Haven't chose any Account, Please Choose the Account");
                        JOptionPane.showMessageDialog( this, jcb, "Select Your Account to Remove", JOptionPane.QUESTION_MESSAGE);
                    }
                    int index=jcb.getSelectedIndex();
                    int value= m.get(index);
                    String str1="Account"+value;
                    String str2="Bank"+value;
                    String str3="Money"+ value;
                    update.put("$unset", new BasicDBObject(str1,"").append(str2, "").append(str3, ""));
                     UpdateResult updateOne = reg.updateOne(new Document("unm", unm), update);
                    setListVal();
                   
               } 
               


// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
   JList list = (JList)evt.getSource();
    if (evt.getClickCount() == 2) {
        int index = list.locationToIndex(evt.getPoint());
        //System.out.println("index: "+index);
        Account a=new Account();
        a.setVisible(true);
        this.dispose();
        a.setData(unm, (String) list.getModel().getElementAt(index));
    }        // TODO add your handling code here: System.out.println(list.getModel().getElementAt(index));
    }//GEN-LAST:event_jList1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Welcome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
