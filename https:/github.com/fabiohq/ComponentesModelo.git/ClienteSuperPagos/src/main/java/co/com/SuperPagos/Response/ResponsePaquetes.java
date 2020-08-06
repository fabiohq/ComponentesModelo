package co.com.SuperPagos.Response;

public class ResponsePaquetes {

		private String preBalance;
		private String posBalance;
		private String tmpBalance;
		private String debt;
		private String message;
		private data data;
		
	
		public data getData() {
			return data;
		}
		public void setData(data data) {
			this.data = data;
		}
		public String getPreBalance() {
			return preBalance;
		}
		public void setPreBalance(String preBalance) {
			this.preBalance = preBalance;
		}
		public String getPosBalance() {
			return posBalance;
		}
		public void setPosBalance(String posBalance) {
			this.posBalance = posBalance;
		}
		public String getTmpBalance() {
			return tmpBalance;
		}
		public void setTmpBalance(String tmpBalance) {
			this.tmpBalance = tmpBalance;
		}
		public String getDebt() {
			return debt;
		}
		public void setDebt(String debt) {
			this.debt = debt;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	
	}

