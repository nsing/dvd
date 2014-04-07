package com.exercise;

/**
 * Dvd service implementation
 */
public class DvdServiceImpl implements DvdService {

	/*
	 * Dvd repository
	 */
	DvdRepository dvdRepository = new DvdRepositoryStub();

	/**
	 * This method returns dvd
	 */
	public Dvd retrieveDvd(String dvdReference) throws DvdNotFoundException {
		//Check reference prefix
		checkReferencePrefix(dvdReference);
		//Get dvd
		Dvd dvd = dvdRepository.retrieveDvd(dvdReference);
		if (null == dvd) { //No dvd retrieved
			throw new DvdNotFoundException();
		}
		return dvd;
	}

	/**
	 * This methods checks dvd reference prefix
	 */
	private void checkReferencePrefix(String dvdReference) {
		if (0 != dvdReference.indexOf("DVD-")) { //Prefix check failure
			throw new RuntimeException("DVD reference must begin with DVD-");
		}
	}

	/**
	 * This method returns dvd summary
	 */
	public String getDvdSummary(String dvdReference) throws DvdNotFoundException {
		//Check dvd reference prefix
		checkReferencePrefix(dvdReference);
		//Get dvd
		Dvd dvd = dvdRepository.retrieveDvd(dvdReference);
		if (null == dvd) { //No dvd retrieved
			throw new DvdNotFoundException();
		}
		//Split review in 11 words using space as a delimiter
		String[] words = dvd.getReview().split("\\s", 11);
		StringBuilder summary = new StringBuilder();
		//Append reference
		summary.append("["); summary.append(dvd.getReference()); summary.append("] ");
		//Append title
		summary.append(dvd.getTitle()); summary.append(" - ");
		//Append up to 10 words
		summary.append(words[0]);
		for(int i=1; i<10 && i <= words.length-1; i++) {
			summary.append(" "); summary.append(words[i]);
		}
		if(words.length > 10) {//Word count is greater than 10
			char a = words[9].charAt(words[9].length()-1);
			if(a == ',') { //If last character is ',' then replace it by an empty String
				summary.replace(summary.length()-1, summary.length(), "");
			}
			//Add "..."
			summary.append("...");
		}
		//Return summary
		return summary.toString();
	}

	/**
	 * This is main method
	 */
	public static void main(String[] args) {
		//Instantiate dvd service implementation
		DvdService dvdService = new DvdServiceImpl();
		//Different cases:
		try {
			System.out.println("Case1:");
			System.out.println(dvdService.getDvdSummary("DVD-TG423"));
		}
		catch(Exception e) {
			System.out.println("Case1 failure:");
			e.printStackTrace();
		}

		try {
			System.out.println("Case2:");
			System.out.println(dvdService.retrieveDvd("INVALID-TEXT"));
		}
		catch(Exception e) {
			System.out.println("Case2 failure:");
			e.printStackTrace();
		}

		try {
			System.out.println("Case3:");
			System.out.println(dvdService.retrieveDvd("DVD-999"));
		}
		catch(Exception e) {
			System.out.println("Case3 failure:");
			e.printStackTrace();
		}

		try {
			System.out.println("Case4:");
			System.out.println(dvdService.retrieveDvd("DVD-TG423").getTitle());
		}
		catch(Exception e) {
			System.out.println("Case4 failure:");
			e.printStackTrace();
		}

		try {
			System.out.println("Case5:");
			System.out.println(dvdService.getDvdSummary("INVALID-TEXT"));
		}
		catch(Exception e) {
			System.out.println("Case5 failure:");
			e.printStackTrace();
		}

		try {
			System.out.println("Case6:");
			System.out.println(dvdService.getDvdSummary("DVD-999"));
		}
		catch(Exception e) {
			System.out.println("Case6 failure:");
			e.printStackTrace();
		}

		try {
			System.out.println("Case7:");
			System.out.println(dvdService.getDvdSummary("DVD-TG423"));
		}
		catch(Exception e) {
			System.out.println("Case7 failure:");
			e.printStackTrace();
		}

		try {
			System.out.println("Case8:");
			System.out.println(dvdService.getDvdSummary("DVD-S765"));
		}
		catch(Exception e) {
			System.out.println("Case8 failure:");
			e.printStackTrace();
		}

	}

}
