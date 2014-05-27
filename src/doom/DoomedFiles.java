package doom;  

public class DoomedFiles {
      
    public static void createProgramInstance()
    {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
           
            @Override
            public void run() 
            {  
                new Splash().display();
            } 
        });
    }
    
    public static void main(String[] args) {
        DoomedFiles.createProgramInstance();
    }
    
}
