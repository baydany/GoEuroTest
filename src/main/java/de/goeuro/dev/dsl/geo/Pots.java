package de.goeuro.dev.dsl.geo;

public class Pots {

	String _type;
	Long _id;
	String name;
	String type;
	GeoPosition geo_position;

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPosition getGeoPosition() {
		return geo_position;
	}

	public void setGeoPosition(GeoPosition geoPosition) {
		this.geo_position = geoPosition;
	}

}
