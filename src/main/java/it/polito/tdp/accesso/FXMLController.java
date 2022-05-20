package it.polito.tdp.accesso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLController {
	
	@FXML
    private Button idAccedi;

    @FXML
    private Button idResetta;

    @FXML
    private Label errore;

    @FXML
    private Label nTentativi;

    @FXML
    private PasswordField password;

    @FXML
    private Label tentativi;

    @FXML
    private TextField userId;

    @FXML
    void resetta(ActionEvent event) {
		idAccedi.setDisable(false);
		idResetta.setDisable(true);
		nTentativi.setText("" );
		tentativi.setText("");
		errore.setText("");
		password.setText("");
		userId.setText("");
    }

    @FXML
    void verificaIDPassword(ActionEvent event) {
    	
    	errore.setText("");
    	
    	if(password.getText().length() == 0 || userId.getText().length() == 0) {
    		
    		errore.setText("Valorizzare sia User che Password" );
    		return;
    	}
    	
    	System.out.println(password.getText());
    	
    	if(password.getText().length() < 7) {
    		errore.setText("La Password deve avere almeno 7 caratteri" );
    		return;
    		
    	}
    	
    	Pattern pattern = Pattern.compile(".*[a-z].*");
        Matcher matcher = pattern.matcher(password.getText());
        boolean matchFound = matcher.find();

        if(matchFound) {
        	System.out.println("a-z Match found");
        } else {
        	errore.setText("La Password deve avere almeno carattere minuscolo" );
        }
        
        if(errore.getText().length()== 0 ) {
            pattern = Pattern.compile(".*[A-Z].*");
            matcher = pattern.matcher(password.getText());
            matchFound = matcher.find();
            if(matchFound && errore == null) {
            	System.out.println("A-Z Match found");
            } else {
            	errore.setText("La Password deve avere almeno carattere maiuscolo" );
            }
        }

        if(errore.getText().length()== 0 ) {
            pattern = Pattern.compile(".*[0-9].*");
            matcher = pattern.matcher(password.getText());
            matchFound = matcher.find();
            if(matchFound) {
            	System.out.println("0-9 Match found");
            } else {
            	errore.setText("La Password deve avere almeno un numero" );
            }
        }

        if(errore.getText().length()== 0 ) {
            pattern = Pattern.compile(".*[!?@#].*");
            matcher = pattern.matcher(password.getText());
            matchFound = matcher.find();
            if(matchFound) {
            	System.out.println("!, ?, @, # Match found");
            } else {
            	errore.setText("La Password deve avere almeno !, ?, @, #" );
            }
        }
        
        if(errore.getText().length()== 0) {
        	errore.setText("Sei acceduto");
    		nTentativi.setText("");
    		tentativi.setText("");
    		idAccedi.setDisable(true);
    		idResetta.setDisable(false);

       	
        } else {
        	if(nTentativi.getText().length() == 0 ) {
        		nTentativi.setText("3");
        		tentativi.setText("  Tentativi rimananti: ");
        	} else {
        		int numeroTentativiRimanenti = Integer.parseInt(nTentativi.getText());
        		numeroTentativiRimanenti--;
        		System.out.println( " tentativo :" + numeroTentativiRimanenti);
        		
        		if (numeroTentativiRimanenti == 0 ) {
        			idAccedi.setDisable(true);
        			idResetta.setDisable(false);
        			nTentativi.setText(" " );
            		tentativi.setText("  Tentativi esauriti ");
        		} else {
        			System.out.println(String.valueOf(numeroTentativiRimanenti));
        			nTentativi.setText(String.valueOf(numeroTentativiRimanenti));
            		tentativi.setText("  Tentativi rimananti: ");
        		}
        		
        	}
        	
        }
   
    }

}
