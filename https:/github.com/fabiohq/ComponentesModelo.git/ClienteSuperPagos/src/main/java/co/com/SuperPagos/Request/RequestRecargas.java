package co.com.SuperPagos.Request;

import co.com.SuperPagos.Response.Rol;

public class RequestRecargas {

	private String productId;
	private String amount;
	private String _channel;
	private String _refId;
	private data data = new data();
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String get_channel() {
		return _channel;
	}
	public void set_channel(String _channel) {
		this._channel = _channel;
	}
	public String get_refId() {
		return _refId;
	}
	public void set_refId(String _refId) {
		this._refId = _refId;
	}
	public data getData() {
		return data;
	}
	public void setData(data data) {
		this.data = data;
	}
}
