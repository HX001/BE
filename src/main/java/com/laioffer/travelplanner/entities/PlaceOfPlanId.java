package com.laioffer.travelplanner.entities;

public class PlaceOfPlanId {
	private Integer placeId;
	private Integer dayId;
	
	public PlaceOfPlanId() {
		
	}
	public PlaceOfPlanId(Integer placeId, Integer dayId) {
		super();
		this.placeId = placeId;
		this.dayId = dayId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayId == null) ? 0 : dayId.hashCode());
		result = prime * result + ((placeId == null) ? 0 : placeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaceOfPlanId other = (PlaceOfPlanId) obj;
		if (dayId == null) {
			if (other.dayId != null)
				return false;
		} else if (!dayId.equals(other.dayId))
			return false;
		if (placeId == null) {
			if (other.placeId != null)
				return false;
		} else if (!placeId.equals(other.placeId))
			return false;
		return true;
	}
	

}
