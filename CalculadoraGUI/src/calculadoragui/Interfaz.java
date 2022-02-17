package calculadoragui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//prueba cambio

public class Interfaz implements ActionListener{

    
    JTextField jt1, jt2;
    Panel pN, pb1, pb3;
    JPanel pS, pb2;
    JButton mc, mr, ms, mMas, mMenos, numeros[], operaciones[];
    String oper[]={"R", "C", "+", "/", "-" ,"*", "="},  ax="";
    float numero1, numero2, resultado, Memoria;//variables para las operaciones
    int tipOp; //para controlar el tipo de operacion que se realiza
    boolean t=false;//control sobre escribir un nuevo numero despues de alguna operacion cambia a true cuando se ha realizado una operacion

    public Interfaz(){

        JFrame jfMain = new JFrame("Calculator");
        jfMain.setLayout(new BorderLayout(4, 4));

        norte();
        sur();

        jfMain.add(pN, BorderLayout.NORTH);
        jfMain.add(pS, BorderLayout.CENTER);

        jfMain.setLocation(100, 80);
        jfMain.setResizable(false);
        jfMain.setVisible(true);
        jfMain.setSize(300, 380);
        jfMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void norte(){

        pN = new Panel(null);        

        jt1 = new JTextField("");
        jt2 = new JTextField("0");

        jt1.setHorizontalAlignment(JTextField.RIGHT); 
        jt2.setHorizontalAlignment(JTextField.RIGHT); 

        //Quitar bordes a los campos de texto
        jt1.setBorder(BorderFactory.createLineBorder(Color.white));
        jt2.setBorder(BorderFactory.createLineBorder(Color.white));

        //desabilitando los campos de texto
        jt1.setEditable(false);
        jt2.setEditable(false);

        jt1.setBackground(Color.white);
        jt2.setBackground(Color.white);

        pN.add(jt1); pN.add(jt2);

        jt1.setBounds(35, 10, 200, 15);
        jt2.setBounds(35, 25, 200, 30);

        pN.setSize(270, 47);
        pN.setVisible(true);

    }

    public void sur(){

        pS = new JPanel(new BorderLayout(6, 50));
        pS.setLayout(new BorderLayout(4, 4));

        botMem();
        botNum();
        botOpe();

        pS.add(pb1, BorderLayout.NORTH);  
        pS.add(pb2, BorderLayout.CENTER); 
        pS.add(pb3, BorderLayout.EAST); 

        pS.setSize(270, 330);
    }

    public void botMem(){

        pb1 = new Panel(null);

        mc = new JButton("MC");  mr = new JButton("MR");
        ms = new JButton("MS"); mMas = new JButton("M+");
        mMenos = new JButton("M-");

        mc.setFont(new Font("Arial", Font.BOLD, 11));
        mr.setFont(new Font("Arial", Font.BOLD, 11));
        ms.setFont(new Font("Arial", Font.BOLD, 11));
        mMas.setFont(new Font("Arial", Font.BOLD, 11));
        mMenos.setFont(new Font("Arial", Font.BOLD, 11));

        mc.setMargin(new Insets(1, 1, 1, 1)); mr.setMargin(new Insets(1, 1, 1, 1));
        ms.setMargin(new Insets(1, 1, 1, 1)); mMas.setMargin(new Insets(1, 1, 1, 1));
        mMenos.setMargin(new Insets(1, 1, 1, 1)); 

        mc.setBounds(35, 0, 33, 33); mr.setBounds(78, 0, 33, 33); ms.setBounds(121, 0, 33, 33);
        mMas.setBounds(164, 0, 33, 33); mMenos.setBounds(207, 0, 33, 33);

        pb1.add(mc); pb1.add(mr); pb1.add(ms); pb1.add(mMas); pb1.add(mMenos);

        mc.addActionListener(this); mr.addActionListener(this); ms.addActionListener(this);
        mMas.addActionListener(this); mMenos.addActionListener(this);

        pb1.setSize(270, 45);
        pb1.setVisible(true);        
    }

    public void botNum(){

        pb2 = new JPanel(null);

        int nx3=121, nx2=121, nx1=121, n3y=0, n2y=43, n1y=86;
        numeros = new JButton[11];

        //*****************************************
        //bloque para crear los botones, añadirlos y asignar numeros
        for (int i=0; i<=10; i++){

            if(i<=9){
                numeros[i] = new JButton(""+i);
                pb2.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);  
            }
            else{
                numeros[i] = new JButton(".");
                pb2.add(numeros[i]);
                numeros[i].setMargin(new Insets(1, 1, 1, 1));
                numeros[i].addActionListener(this);
            }
        }

        //******************************************
        //bloque para posicionar botones
        for(int i=10; i>=0; i--){

            if(i==10){
                numeros[i].setBounds(121, 129, 35, 35);
            }
            else{
                if(i<=9 && i>=7){
                    numeros[i].setBounds(nx3, n3y, 35, 35);
                    nx3-=43;
                }
                else if(i<=6 && i>=4){   
                    n3y+=43;                    
                    numeros[i].setBounds(nx2, n2y, 35, 35);
                    nx2-=43;
                }
                else if(i<=3 && i>=1){
                    n3y+=43;                    
                    numeros[i].setBounds(nx1, n1y, 35, 35);
                    nx1-=43;
                }
                else if(i==0){
                    numeros[i].setBounds(35, 129, 78, 35);                    
                }
            }                
        }

        pb2.setSize(170, 150);
        pb2.setVisible(true);
    }

    public void botOpe(){

        pb3 = new Panel(null);

        int c=0, x=0, y=0;

        operaciones = new JButton[7];

        for(int i=0; i<=6; i++){
            if(c<=1){

                operaciones[i] = new JButton(oper[i]);
                pb3.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 30, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;               
            }
            else{
                if(i==6){
                    x=0; y+=43;
                    operaciones[i] = new JButton(oper[i]);
                    pb3.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 65, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;
                }
                else{
                    c=0;
                    x=0; y+=43;
                    operaciones[i] = new JButton(oper[i]);
                    pb3.add(operaciones[i]);

                    operaciones[i].setBounds(x, y, 30, 35);

                    operaciones[i].setMargin(new Insets(1, 1, 1, 1));
                    operaciones[i].addActionListener(this);
                    x+=33;
                    c++;   
                }                             
            }                

        }

        pb3.setVisible(true);
        pb3.setSize(120, 200);
    }

    public boolean isN(String ax){

        try{
            int n = Integer.parseInt(ax);
            return true;
        }catch(NumberFormatException e){
            return false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String  op="";

        if(isN(e.getActionCommand())){ oprimir_numeros(e);            
        }
        else{//cuando se oprime el resto de botones

            if(e.getActionCommand().equals("R") )oprimir_resto_botones();
            if(e.getActionCommand().equals("C") ){ reiniciar_valores(); }   
            if(e.getActionCommand().equals("MC"))limpiar_memoria();
            if(e.getActionCommand().equals("MR"))mostra_valor_memoria();
            if(e.getActionCommand().equals("MS"))guarda_valor_memoria();
            if(e.getActionCommand().equals("M+"))sumar_memoria();
            if(e.getActionCommand().equals("M-"))restar_memoria();    
            if(e.getActionCommand().equals("."))usar_punto_decimal();
            if(e.getActionCommand().equals("+") )suma();
                if(e.getActionCommand().equals("-") )resta();
                if(e.getActionCommand().equals("*") )multiplicacion();
                if(e.getActionCommand().equals("/") )division();
                if(e.getActionCommand().equals("=") && !jt2.getText().equals(""))resultado();
        }        
    }       

    private void resultado() throws NumberFormatException {
        t = true;
        if(tipOp==1){//operacion para la suma
            tipOp = 0;
            ax="";
            ax+=jt1.getText() + jt2.getText();
            jt1.setText(ax);
            numero2 = Float.parseFloat(jt2.getText());
            resultado=numero1+numero2;
            jt2.setText(String.valueOf(resultado));
        }
        else if(tipOp==2){ //operacion para la resta
            tipOp = 0;
            ax="";
            ax+=jt1.getText()+jt2.getText();
            jt1.setText(ax);
            numero2 = Float.parseFloat(jt2.getText());
            resultado=numero1-numero2;
            jt2.setText(String.valueOf(resultado));
        }
        if(tipOp==3){ //operacion para la multiplicacion
            tipOp = 0;
            ax="";
            ax+=jt1.getText()+jt2.getText();
            jt1.setText(ax);
            numero2 = Float.parseFloat(jt2.getText());
            resultado=numero1*numero2;
            jt2.setText(String.valueOf(resultado));
        }
        if(tipOp==4){ //operacion para la multiplicacion
            if(Float.parseFloat(jt2.getText())!=0){
                tipOp = 0;
                ax="";
                ax+=jt1.getText()+jt2.getText();
                jt1.setText(ax);
                numero2 = Float.parseFloat(jt2.getText());
                resultado=numero1/numero2;
                jt2.setText(String.valueOf(resultado));
            }
            
        }
    }

    private void division() throws NumberFormatException {
        //cuando se decide dividir
        numeros[10].setEnabled(true);
        ax="";
        if(tipOp==4){
            
        }else if(tipOp==0){//validacion para no chocar con otras operaciones
            if(jt1.getText().equals("")){
                numero1 = Float.parseFloat(jt2.getText());
                ax += jt1.getText()+jt2.getText();
                jt1.setText(ax+" / ");
                jt2.setText("");
                tipOp = 4;
            }
            else{
                if(!t){//validacion para nueva operacion
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt2.getText();
                    jt1.setText(ax+" / ");
                    jt2.setText("");
                    tipOp = 4;
                }
                else{//usar otras operaciones con la suma
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt1.getText();
                    jt1.setText(ax+" / ");
                    jt2.setText("");
                    tipOp = 4;
                }
            }
        }
    }

    private void multiplicacion() throws NumberFormatException {
        //cuando se decide multiplicar
        numeros[10].setEnabled(true);
        ax="";
        if(tipOp==3){
            
        }else if(tipOp==0){//validacion para no chocar con otras operaciones
            if(jt1.getText().equals("")){
                numero1 = Float.parseFloat(jt2.getText());
                ax += jt1.getText()+jt2.getText();
                jt1.setText(ax+" * ");
                jt2.setText("");
                tipOp = 3;
            }
            else{
                if(!t){//validacion para nueva operacion
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt2.getText();
                    jt1.setText(ax+" * ");
                    jt2.setText("");
                    tipOp = 3;
                }
                else{//usar otras operaciones con la suma
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt1.getText();
                    jt1.setText(ax+" * ");
                    jt2.setText("");
                    tipOp = 3;
                }
            }
        }
    }

    private void resta() throws NumberFormatException {
        //cuando se decide restar
        numeros[10].setEnabled(true);
        ax="";
        if(tipOp==2){
            
        }else if(tipOp==0){//validacion para no chocar con otras operaciones
            if(jt1.getText().equals("")){
                numero1 = Float.parseFloat(jt2.getText());
                ax += jt1.getText()+ jt2.getText();
                jt1.setText(ax+" - ");
                jt2.setText("");
                tipOp = 2;
            }
            else{
                if(!t){//validacion para nueva operacion
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt2.getText();
                    jt1.setText(ax+" - ");
                    jt2.setText("");
                    tipOp = 2;
                }
                else{//usar otras operaciones con la suma
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt1.getText();
                    jt1.setText(ax+" - ");
                    jt2.setText("");
                    tipOp = 2;
                }
            }
        }
    }

    private void suma() throws NumberFormatException {
        //boton suma
        numeros[10].setEnabled(true);
        ax="";
        if(tipOp==1){
            
        }else if(tipOp==0 ){//validacion para no chocar con otras operaciones
            if(jt1.getText().equals("") ){
                numero1 = Float.parseFloat(jt2.getText());
                ax += jt1.getText()+jt2.getText();
                jt1.setText(ax+" + ");
                jt2.setText("");
                tipOp = 1;
            }
            else {
                if(!t){//validacion para nueva operacion
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt2.getText();
                    jt1.setText(ax+" + ");
                    jt2.setText("");
                    tipOp = 1;
                }
                else{//usar otras operaciones con la suma
                    numero1 = Float.parseFloat(jt2.getText());
                    ax += jt1.getText();
                    jt1.setText(ax+" + ");
                    jt2.setText("");
                    tipOp = 1;
                }
            }
        }
    }

    private void usar_punto_decimal() {
        //usar el punto para los decimales
        ax="";
        if(numeros[10].isEnabled()){
            numeros[10].setEnabled(false);
            ax = jt2.getText() +".";
            jt2.setText(ax);
        }
    }

    private void restar_memoria() throws NumberFormatException {
        //restar valor de la pantalla con el valor de la memoria
        Memoria -= Float.parseFloat(jt2.getText());
    }

    private void sumar_memoria() throws NumberFormatException {
        //sumar valor de la pantalla con el valor de la memoria
        Memoria += Float.parseFloat(jt2.getText());
    }

    private void guarda_valor_memoria() throws NumberFormatException {
        //guardar un valor en la memoria
        ms.setForeground(Color.red);
        Memoria = Float.parseFloat(jt2.getText());
    }

    private void mostra_valor_memoria() {
        //para mostrar valor almacenado en la memoria
        jt1.setText("");
        jt2.setText(String.valueOf(Memoria));
    }

    private void limpiar_memoria() {
        //para limpiar la memoria de la calculadora
        ms.setForeground(Color.black);
        jt1.setText(""); jt2.setText("0");
        Memoria=0;
    }

    private void reiniciar_valores() {
        //para reiniciar valores y limpiar pantalla
        tipOp=0; numero1 = 0; numero2 =0; resultado=0; jt1.setText(""); jt2.setText("0"); ax="";
    }

    private void oprimir_resto_botones() throws NumberFormatException {
        jt1.setText("");
        Float a = Float.parseFloat(jt2.getText());
        jt2.setText(""+Math.sqrt(a));
    }

    private void oprimir_numeros(ActionEvent e) {
        //cuando se oprimen numeros
        
        if(jt1.getText().equals("")){
            ax += e.getActionCommand();
            jt2.setText(ax);
        }
        else{
            if(tipOp==0){
                if(t){
                    ax="";
                    
                    jt1.setText(jt2.getText());
                    ax += e.getActionCommand();
                    jt2.setText(ax);
                    t = false;
                }
                else{
                    ax="";
                    ax += jt2.getText()+e.getActionCommand();
                    jt2.setText(ax);
                }
            }else{
                ax="";
                ax += jt2.getText()+e.getActionCommand();
                jt2.setText(ax);
            }
        }
    }
}