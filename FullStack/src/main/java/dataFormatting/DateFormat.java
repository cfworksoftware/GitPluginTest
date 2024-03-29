package dataFormatting;

public class DateFormat {

	public String formatDateCommaSeperated(String currentDateForm) {
		String[] arrOfStr = null; 
		String formattedDate = null;
		if (currentDateForm.contains(",")){
			arrOfStr = currentDateForm.split(", ", 2); 
	//		System.out.println("Date split: " + arrOfStr[0] + " : " + arrOfStr[1]);
		}
		String year = arrOfStr[1].split(" ")[0];
		String month = convertMonthtoInteger(arrOfStr[0].split(" ")[0]);
		String day = arrOfStr[0].split(" ")[1];
		
		formattedDate = year + "-" + month + "-" + day;
//		System.out.println("Formatted Date: " + formattedDate);
		return formattedDate;
	}
	
    public String formatDateTwoDigitMonth(String currentDateForm) {
		//Assume date is in either DD/MM/YYYY or American system with spaces MMM DD YYYY need to convert to YYYY-MM-DD
		String[] arrOfStr = null; 
		String formattedDate = null;
		if (currentDateForm.contains("/")){
			arrOfStr = currentDateForm.split("/", 3);  	//		System.out.println("Identified date format with '/'");
			formattedDate = arrOfStr[2] + "-" + arrOfStr[1]+ "-" + arrOfStr[0];
		}
/*		else if (currentDateForm.contains(" ")){
			arrOfStr = currentDateForm.split(" ", 8);  	//		System.out.println("Identified date format with spaces");
			String monthCnt = null;
			switch(arrOfStr[2])
			{
			case("Jan"):
				monthCnt = "01";
				break;
			case("Feb"):
				monthCnt = "02";
				break;
			case("Mar"):
				monthCnt = "03";
				break;
			case("Apr"):
				monthCnt = "04";
				break;
			case("Mai"):
				monthCnt = "05";
				break;
			case("Jun"):
				monthCnt = "06";
				break;
			case("Jul"):
				monthCnt = "07";
				break;
			case("Aug"):
				monthCnt = "08";
				break;
			case("Sep"):
				monthCnt = "09";
				break;
			case("Oct"):
				monthCnt = "10";
				break;
			case("Nov"):
				monthCnt = "11";
				break;
			case("Dec"):
				monthCnt = "12";
				break;
			default:
				monthCnt = "00";
				break;
			}
			formattedDate = arrOfStr[4]+ "-" + monthCnt + "-" + arrOfStr[3].replace(",", "");			
		}*/
	//	System.out.println("Format Date:" + formattedDate);
		return formattedDate;
		}	
		
	public String convertMonthtoInteger(String threeLetterMonth) {
		String monthCnt;
		switch(threeLetterMonth)
		{
		case("Jan"):
			monthCnt = "01";
			break;
		case("Feb"):
			monthCnt = "02";
			break;
		case("Mar"):
			monthCnt = "03";
			break;
		case("Apr"):
			monthCnt = "04";
			break;
		case("Mai"):
			monthCnt = "05";
			break;
		case("Jun"):
			monthCnt = "06";
			break;
		case("Jul"):
			monthCnt = "07";
			break;
		case("Aug"):
			monthCnt = "08";
			break;
		case("Sep"):
			monthCnt = "09";
			break;
		case("Oct"):
			monthCnt = "10";
			break;
		case("Nov"):
			monthCnt = "11";
			break;
		case("Dec"):
			monthCnt = "12";
			break;
		default:
			monthCnt = "00";
			break;
		}
		return monthCnt;
	}
}
