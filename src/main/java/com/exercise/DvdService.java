package com.exercise;

public interface DvdService {

	Dvd retrieveDvd(String dvdReference) throws DvdNotFoundException;

	String getDvdSummary(String dvdReference) throws DvdNotFoundException;

}
