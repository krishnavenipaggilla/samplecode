package com.nimbus.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bson.Document;
import org.openqa.selenium.WebDriver;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.nimbus.testcase.TestCases;

import static com.mongodb.client.model.Projections.*;
import utility.ConfigReader;
/*
 * Class Name : MongoDbConnection

 * Description:	Get case details from MongoDB
 */
public class MongoDbConnection {

	ConfigReader config = new ConfigReader();

	WebDriver driver;
	Logger Log;
	MongoClient mongoClient;
	MongoDatabase mydatabase;
	

	public MongoDbConnection(WebDriver ldriver) {
		this.driver = ldriver;
		initializeEnv();
	}

	public void getCaseDetails(boolean useAPI, boolean useTempCase) {

		try {

			mongoClient = new MongoClient(new MongoClientURI(GlobalValues.DBURL.toString()));
			mydatabase = mongoClient.getDatabase(GlobalValues.DBName);
			if (useAPI == true) {
				try{
				GlobalValues.caseId = mydatabase.getCollection("cases")
						.find(Filters.and(Filters.eq("member.firstName", "APIFirst Auto")))
						.sort(new BasicDBObject("createdDate", -1)).first().get("_id").toString();
				}
				catch(Exception e){
					GlobalValues.caseId = null;
					Log.info("Case Id From API Call is Null");
				}
				
				Log.info("Case Id From API Call = " + GlobalValues.caseId);
			} else if (useTempCase) {
				GlobalValues.caseId = GlobalValues.caseIdFromTempMember;
			}
			if (useAPI == true || useTempCase == true) {
				if (GlobalValues.caseId == null || GlobalValues.caseId.isEmpty()) {
					getLastCaseIfNull();
				}
			}
			getCaseDetailsFromMongo();
		
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;

		} finally {
			mongoClient.close();
		}
	}

	public void initializeEnv() {
		try {

			Log = Logger.getLogger("connectToMongo.class");
			PropertyConfigurator.configure("log4j.properties");

			if (GlobalValues.Environment.equalsIgnoreCase("SIT")) {
				GlobalValues.DBURL = "mongodb://nimbussit:Nim18SIT@va33tlvmdb302:37043/NICU1S?ssl=true";
				GlobalValues.DBName = "NICU1S";
				GlobalValues.DBUserName = "nimbussit";
				GlobalValues.DBPassword = "Nim18SIT";

			} else if (GlobalValues.Environment.equalsIgnoreCase("DEV")) {
				GlobalValues.DBURL = "mongodb://nimbusDev:ArR&GX8Z@va33dlvmdb304:37043/NICU1D?ssl=true";
				GlobalValues.DBName = "NICU1D";
				GlobalValues.DBUserName = "nimbusDev";
				GlobalValues.DBPassword = "ArR&GX8Z";

			} else if (GlobalValues.Environment.equalsIgnoreCase("SANDBOX")) {
				GlobalValues.DBURL = "mongodb://nimbusDev:ArR&GX8Z@va33dlvmdb304:37043/NICU2D?ssl=true";
				GlobalValues.DBName = "NICU2D";
				GlobalValues.DBUserName = "nimbusDev";
				GlobalValues.DBPassword = "ArR&GX8Z";

			} else if (GlobalValues.Environment.equalsIgnoreCase("UAT")) {
				GlobalValues.DBURL = "mongodb://nimbusuat:Nim18UAT@va33tlvmdb303:37043/NICU1U?ssl=true";
				GlobalValues.DBName = "NICU1U";
				GlobalValues.DBUserName = "nimbusuat";
				GlobalValues.DBPassword = "Nim18UAT";

			}else if (GlobalValues.Environment.equalsIgnoreCase("IMPLEMENTATION DEV")) {
				GlobalValues.DBURL = "mongodb://hipDev:Gkl9EFfzimfbQLLb@va33dlvmdb304:37043/HIP1D?ssl=true";
				GlobalValues.DBName = "HIP1D";
				GlobalValues.DBUserName = "hipDev";
				GlobalValues.DBPassword = "Gkl9EFfzimfbQLLb";

				}
			else if (GlobalValues.Environment.equalsIgnoreCase("IMPLEMENTATION SIT")) {
				GlobalValues.DBURL = "mongodb://hipDev:HpI%40S21A@va33tlvmdb302:37043/HIP1S?ssl=true";
				GlobalValues.DBName = "HIP1S";
				GlobalValues.DBUserName = "hipDev";
				GlobalValues.DBPassword = "HpI@S21A";

				}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	

	public void getCaseDetailsFromMongo() {

		try {

			int caseIdInt = Integer.parseInt(GlobalValues.caseId);
			Document casesDocument = mydatabase.getCollection("cases").find(Filters.and(Filters.eq("_id", caseIdInt)))
					.first();

			GlobalValues.DBCaseStatus = casesDocument.getString("status");
			Log.info("Case Status : " + GlobalValues.DBCaseStatus);
			if (casesDocument.containsKey("caseOwner")) {
				if (casesDocument.get("caseOwner", Document.class).containsKey("firstName")) {
					GlobalValues.DBCaseOwnerFirstName = casesDocument.get("caseOwner", Document.class)
							.getString("firstName").toLowerCase();
				}
				if (casesDocument.get("caseOwner", Document.class).containsKey("lastName")) {
					GlobalValues.DBCaseOwnerLastName = casesDocument.get("caseOwner", Document.class)
							.getString("lastName").toLowerCase();
				}

				if (casesDocument.get("caseOwner", Document.class).containsKey("displayName")) {
					GlobalValues.DBCaseOwnerDisplayName = casesDocument.get("caseOwner", Document.class)
							.getString("displayName");
				}
				Log.info("CaseOwnerFirstName = " + GlobalValues.DBCaseOwnerFirstName);
				Log.info("CaseOwnerLastName = " + GlobalValues.DBCaseOwnerLastName);
				Log.info("CaseOwnerDisplayName = " + GlobalValues.DBCaseOwnerDisplayName);
			}

			if (casesDocument.containsKey("member")) {
				if (casesDocument.get("member", Document.class).containsKey("memberId")) {
					GlobalValues.DBMemberId = casesDocument.get("member", Document.class).get("memberId").toString();
				}

				// if (casesDocument.get("member",
				// Document.class).containsKey("phoneNumber")) {
				// GlobalValues.DBPhoneNumber = casesDocument.get("member",
				// Document.class).get("phoneNumber")
				// .toString();
				// }

				Log.info("Member ID = " + GlobalValues.DBMemberId);
			}

			if (casesDocument.containsKey("member")) {
				if (casesDocument.get("member", Document.class).containsKey("firstName")) {
					GlobalValues.DBFirstNameFromCasesCollection = casesDocument.get("member", Document.class)
							.get("firstName").toString();
					Log.info("FirstNameFromCasesCollection = " + GlobalValues.DBFirstNameFromCasesCollection);
				}

			}

			if (casesDocument.containsKey("member")) {
				if (casesDocument.get("member", Document.class).containsKey("lastName")) {
					GlobalValues.DBLastNameFromCasesCollection = casesDocument.get("member", Document.class)
							.get("lastName").toString();
					Log.info("LastNameFromCasesCollection = " + GlobalValues.DBLastNameFromCasesCollection);
				}

			}

			if (casesDocument.containsKey("riskOrAcuity")) {
				GlobalValues.DBRiskOrAcuity = casesDocument.get("riskOrAcuity").toString();
			}

			if (casesDocument.containsKey("primaryRsnForMgmt")) {
				GlobalValues.DBPrimaryRsnForMgmt = casesDocument.get("primaryRsnForMgmt").toString();
			}

			Log.info("Risk Or Acuity = " + GlobalValues.DBRiskOrAcuity);
			Log.info("DBPrimaryReasonForMgmt= " + GlobalValues.DBPrimaryRsnForMgmt);

			if (casesDocument.containsKey("programReferred")) {
				List<Document> programReferredDoc = (List<Document>) casesDocument.get("programReferred");
				int lastIndex = programReferredDoc.size() - 1;
				GlobalValues.DBProgramName = programReferredDoc.get(lastIndex).get("programName").toString();
				GlobalValues.DBProgramNameActiveStatus = programReferredDoc.get(lastIndex).get("active").toString();
				GlobalValues.DBProgramNamePrimaryStatus = programReferredDoc.get(lastIndex).get("primary").toString();

				Log.info("Program Name = " + GlobalValues.DBProgramName);
				Log.info("Program Name ActiveStatus = " + GlobalValues.DBProgramNameActiveStatus);
				Log.info("Program Name PrimaryStatus = " + GlobalValues.DBProgramNamePrimaryStatus);

			}

			Document memberDocument = mydatabase.getCollection("member")
					.find(Filters.and(Filters.eq("_id", Integer.parseInt(GlobalValues.DBMemberId)))).first();
			
//			System.out.println(memberDocument.size() + "," + memberDocument);

			Document membernode = mydatabase.getCollection("member")
					.find(Filters.and(Filters.eq("_id", Integer.parseInt(GlobalValues.DBMemberId)))).projection(fields(include("contactPhones"),excludeId())).first();	
					
//					mydatabase.getCollection("member").find().projection(fields(include("contactPhones"),excludeId())).first();
			

			
			String s = membernode.toString();
			

			String[] s1 = (s.split("\\{_"))[1].split("}")[0].split(",");
			for(int i =0;i<s1.length;i++){
				if(s1[i].contains("phoneType")){
					GlobalValues.DBPhoneType = (s1[i].split("="))[1].trim();
				}
			}
			
			Log.info("PhoneType = " + GlobalValues.DBPhoneType);
			

			
			if (memberDocument.containsKey("memberId")) {
				GlobalValues.DBMemberId2 = memberDocument.get("memberId").toString();
			}

			Log.info("Member ID 2 = " + GlobalValues.DBMemberId2);

			
			
			if (memberDocument.get("memberName", Document.class).containsKey("firstName")) {
				GlobalValues.DBMemberFirstName = memberDocument.get("memberName", Document.class).get("firstName")
						.toString();
			}

			if (memberDocument.get("memberName", Document.class).containsKey("lastName")) {
				GlobalValues.DBMemberLastName = memberDocument.get("memberName", Document.class).get("lastName")
						.toString();
			}
			
//			if (memberDocument.get("contactPhones", Document.class).containsKey("phoneType")) {
//				GlobalValues.DBPhoneType = memberDocument.get("contactPhones", Document.class).get("phoneType")
//						.toString();
//			}
			
			if (memberDocument.containsKey("expressConsent")) {
				GlobalValues.DBPhoneConsent = memberDocument.get("expressConsent").toString();
			}

			// if(memberDocument.get("memberName",
			// Document.class).containsKey("nickname")){
			// GlobalValues.DBNickname = memberDocument.get("memberName",
			// Document.class).get("nickname").toString();
			// }
			// GlobalValues.DBMemberFullName = memberDocument.get("memberName",
			// Document.class).get("fullName").toString();

			DateFormat dateFormat_Map = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat_Map.setTimeZone(TimeZone.getTimeZone("UTC"));
			String dateString = new String();

			dateFormat_Map = new SimpleDateFormat("MM/dd/yyyy");
			dateFormat_Map.setTimeZone(TimeZone.getTimeZone("UTC"));
			dateString = dateFormat_Map.format(memberDocument.getDate("dob"));
			GlobalValues.DBDOB = dateString;

			Log.info("DOB = " + GlobalValues.DBDOB);

			// GlobalValues.DBDOB = memberDocument.get("dob");
			if (memberDocument.containsKey("gender")) {
				GlobalValues.DBGender = memberDocument.get("gender").toString();
			}

			if (memberDocument.containsKey("ethnicity")) {
				GlobalValues.DBEthnicity = memberDocument.get("ethnicity").toString();
			}
			// GlobalValues.DBExpressConsent =
			// memberDocument.get("expressConsent").toString();
			// GlobalValues.DBBestTimeToCall =
			// memberDocument.get("bestTimeToCall").toString();

			if (memberDocument.containsKey("timeZone")) {
				GlobalValues.DBTimeZone = memberDocument.get("timeZone").toString();
			}

			if (memberDocument.containsKey("email")) {
				GlobalValues.DBEmail = memberDocument.get("email").toString();
			}

			if (memberDocument.containsKey("preferdCommunicationMethod")) {
				GlobalValues.DBPreferdCommunicationMethod = memberDocument.get("preferdCommunicationMethod").toString();
			}

			if (memberDocument.containsKey("memberId")) {
				GlobalValues.DBMemberIdOfMember = memberDocument.get("memberId").toString();
			}

			if (memberDocument.containsKey("bestDayToCall")) {
				GlobalValues.DBBestDayToCall = memberDocument.get("bestDayToCall").toString();
			}

			if (memberDocument.containsKey("addresses")) {
				List<Document> cp = (List<Document>) memberDocument.get("addresses");
				if (cp.size() > 0 && cp.get(0).containsKey("fullAddress")) {
					GlobalValues.DBAddress = cp.get(0).get("fullAddress").toString();
				}
			}

			if (memberDocument.containsKey("contactPhones")) {

				List<Document> cp = (List<Document>) memberDocument.get("contactPhones");
				if (cp.size() > 0 && cp.get(0).containsKey("number")) {
					GlobalValues.DBContactPhone = cp.get(0).get("number").toString();
				}
			}

			if (memberDocument.containsKey("langs")) {

				List<Document> cp = (List<Document>) memberDocument.get("langs");
				if (cp.size() > 0 && cp.get(0).containsKey("language")) {
					GlobalValues.DBLanguage = cp.get(0).get("language").toString();
				}
			}

			Log.info("Member FirstName = " + GlobalValues.DBMemberFirstName);
			Log.info("Member LastName = " + GlobalValues.DBMemberLastName);
			Log.info("Member FullName = " + GlobalValues.DBMemberFullName);

			Log.info("Member DOB= " + GlobalValues.DBDOB);
			Log.info("Gender= " + GlobalValues.DBGender);
			Log.info("Ethinicity= " + GlobalValues.DBEthnicity);
			Log.info("Express Consent = " + GlobalValues.DBExpressConsent);
			Log.info("Best Time To Call = " + GlobalValues.DBBestTimeToCall);
			Log.info("TimeZone = " + GlobalValues.DBTimeZone);
			Log.info("Email = " + GlobalValues.DBEmail);
			Log.info("PreferredCommunicationMethod = " + GlobalValues.DBPreferdCommunicationMethod);
			Log.info("MemberId Of Member = " + GlobalValues.DBMemberIdOfMember);
			Log.info("Member Address = " + GlobalValues.DBAddress);
			Log.info("Member Contact Phone = " + "" + GlobalValues.DBContactPhone);
			Log.info("Member Language = " + GlobalValues.DBLanguage);
			Log.info("Best Day To Call = " + GlobalValues.DBBestDayToCall);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {

			mongoClient.close();
		}
	}

	public String getValue(Document doc, String key) {
		if (doc.containsKey(key)) {
			return doc.get(key).toString();
		}
		return null;

	}

	public void getLastCaseIfNull() {

		MongoCollection<Document> casesDocument = mydatabase.getCollection("cases");

		long casesCount = casesDocument.count();
		Log.info("No of cases in cases collection = " + casesCount);

		long caseid = casesDocument.find().sort(new BasicDBObject("createdDate", -1)).first().getLong("_id");
		Log.info("Case Id From API Call  = " + caseid);

		GlobalValues.caseId = Long.toString(caseid);
		

	}
}
