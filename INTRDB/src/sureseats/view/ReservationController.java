package sureseats.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sureseats.model.ReservationService;
import sureseats.model.SureseatsDB;

public class ReservationController {

	private ReservationService rs;
	private SureseatsDB sureseatsDB;
    @FXML
    private Button to_Back;

    @FXML
    private Text Seat_text;

    @FXML
    private Text Movie_Title;

    @FXML
    private Text Confirmation_Code;

    @FXML
    private Text Quantity_price;

    @FXML
    private Text Reservation_Code;

    @FXML
    private Text Total;
    
    public void initialize()
    {
    	sureseatsDB = new SureseatsDB();
    	rs= new ReservationService(sureseatsDB);
    	Reservation_Code.setText(rs.generateCode());
    }
    
    @FXML
    void toback(ActionEvent event) {

    }

}
