package com.nanoboat.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.nanoboat.bean.Customer;
import com.nanoboat.dao.UserDao;

@Path("/customer")
public class CustomerService {

	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTest() {
		
		 return "ok";
	}
	
	@GET
	@Path("/getallcustomer")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRestFul() {
		UserDao userDao = new UserDao();

		 String getUserList = new Gson().toJson(userDao.getAllUsers());
		 System.out.println("Customer list: " + getUserList);
		 return getUserList;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/customer")
	public void postPerson(String inputStream) throws Exception {

		Customer customer = new Gson().fromJson(inputStream, Customer.class);
		//CustomerService service = new CustomerService();
		//service.createCustomerInQuickBook(customer);
	}
//
//	@POST
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.TEXT_PLAIN })
//	@Path("/invoice")
//	public void createInvoice(String inputStream) throws Exception {
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.registerTypeAdapter(LineDetailTypeEnum.class,
//				new AttributeScopeDeserializer());
//		Gson gson = gsonBuilder.create();
//		Invoice invoice = gson.fromJson(inputStream, Invoice.class);
//		try {
//			invoice = dataService.add(invoice);
//		} catch (FMSException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@GET
//	@Path("/email/{invoiceId}/{CustomerEmailid}")
//	@Consumes({ MediaType.TEXT_PLAIN })
//	@Produces(MediaType.TEXT_PLAIN)
//	public String checkConnection(@PathParam("invoiceId") String invoiceId,
//			@PathParam("CustomerEmailid") String CustomerEmailid)
//			throws IOException, FMSException, URISyntaxException {
//		String pdfUrl = Constants.BASE_URL + prop.getProperty("companyId")
//				+ "/invoice/" + invoiceId + "/pdf";
//		URI url = new URI(pdfUrl);
//		DefaultHttpClient client = new DefaultHttpClient();
//		client.getParams().setParameter(Constants.HTTP_CHARSET,
//				Constants.CHARSET_TYPE);
//		HttpRequest httpRequest = new HttpGet(url);
//		httpRequest.addHeader(Constants.CONTENT_TYPE, Constants.DOCUMENT_TYPE);
//		httpRequest.addHeader(Constants.ACCEPT_HEADER, Constants.DOCUMENT_TYPE);
//
//		oauth.authorize((HttpRequestBase) httpRequest);
//		HttpResponse httpResponse = null;
//
//		try {
//			HttpHost target = new HttpHost(url.getHost(), -1, url.getScheme());
//			httpResponse = client.execute(target, httpRequest);
//			System.out.println("Connection status : "
//					+ httpResponse.getStatusLine());
//			File file = File.createTempFile("pattern", ".pdf");
//			file.setWritable(true);
//			FileOutputStream outputStream = new FileOutputStream(file);
//			httpResponse.getEntity().writeTo(outputStream);
//			EmailService service = new EmailService();
//			service.sendEmail(file.getPath(), CustomerEmailid);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "sent";
//	}
//
//	@GET
//	@Path("/emailInvoice/{invoiceId}")
//	@Consumes({ MediaType.TEXT_PLAIN })
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getInvoiceDetail(@PathParam("invoiceId") String invoiceId)
//			throws FMSException, URISyntaxException {
//
//		String pdfUrl = Constants.BASE_URL + prop.getProperty("companyId")
//				+ "/invoice/"+invoiceId;
//		URI url = new URI(pdfUrl);// "https://qb.sbfinance.intuit.com/v3/company/1376583630/query?query=select%20%2A%20from%20invoice%20where%20id%3D%279%27");
//		DefaultHttpClient client = new DefaultHttpClient();
//		client.getParams().setParameter(Constants.HTTP_CHARSET,
//				Constants.CHARSET_TYPE);
//		HttpRequest httpRequest = new HttpGet(url);
//		httpRequest.addHeader(Constants.CONTENT_TYPE, "application/json");
//		httpRequest.addHeader(Constants.ACCEPT_HEADER, "application/json");
//
//		oauth.authorize((HttpRequestBase) httpRequest);
//		HttpResponse httpResponse = null;
//
//		try {
//			HttpHost target = new HttpHost(url.getHost(), -1, url.getScheme());
//			httpResponse = client.execute(target, httpRequest);
//			System.out.println("Connection status : "
//					+ httpResponse.getStatusLine());
//			String json = EntityUtils.toString(httpResponse.getEntity());
//			
//			GsonBuilder gsonBuilder = new GsonBuilder();
//			gsonBuilder.registerTypeAdapter(Date.class,
//					new DateTypeAdapter());
//			gsonBuilder.registerTypeAdapter(ETransactionStatusEnum.class, new ETransactionStatusEnumScopeDeserializer());
//			gsonBuilder.registerTypeAdapter(GlobalTaxCalculationEnum.class, new GlobalTaxCalculationEnumEnumScopeDeserializer());
//			gsonBuilder.registerTypeAdapter(PrintStatusEnum.class, new PrintStatusEnumScopeDeserializer());
//			gsonBuilder.registerTypeAdapter(EmailStatusEnum.class, new EmailStatusEnumScopeDeserializer());
//			gsonBuilder.registerTypeAdapter(DeliveryTypeEnum.class, new DeliveryTypeEnumScopeDeserializer());
//			gsonBuilder.registerTypeAdapter(LineDetailTypeEnum.class, new AttributeScopeDeserializer());
//			Gson gson = gsonBuilder.create();
//			json = json.substring(json.indexOf(":") + 1, json.lastIndexOf(","));
//			Pattern pattern = Pattern.compile("[^:](?:\")([A-Z])");
//			Matcher matcher = pattern.matcher(json);
//			while (matcher.find()) {
//				String str=json.substring(matcher.start(1),matcher.end(1));
//				json=json.substring(0,matcher.start(1))+str.toLowerCase()+json.substring(matcher.end(),json.length());
//			}
//			json.replace("sent", "Sent");
//			Invoice invoice = gson.fromJson(json, Invoice.class);
//			createReport(invoice);
//			System.out.println("test");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "done";
//	}
//
//	public static void main(String args[]) {
//
//		createReport(new Invoice());
//
//	}
//
//	public static void createReport(Invoice invoice) {
//		String destFileName = "/Users/shalinigoyal/Documents/workspace/QuickBookIntegration/WebContent/WEB-INF/Invoice.jasper";
//		String sourceFileName = "/Users/shalinigoyal/Documents/workspace/QuickBookIntegration/WebContent/WEB-INF/invoice.jrxml";
//
//		System.out.println("Compiling Report Design ...");
//		try {
//			/**
//			 * Compile the report to a file name same as the JRXML file name
//			 */
//			JasperCompileManager.compileReportToFile(sourceFileName,
//					destFileName);
//			
//			 List<Invoice> dataList=new ArrayList<Invoice>();
//			 dataList.add(invoice);
//		      //List<Line> dataList = invoice.getLine();
//
//
//		      JRBeanCollectionDataSource beanColDataSource =
//		      new JRBeanCollectionDataSource(dataList,false);
//
//			Map params = new HashMap();
//			params.put("invoiceNumber", invoice.getDocNumber());
//			
//			String printFileName = JasperFillManager.fillReportToFile(
//					destFileName, params, beanColDataSource);
//
//			JasperExportManager
//					.exportReportToPdfFile(
//							printFileName,
//							"/Users/shalinigoyal/Documents/workspace/QuickBookIntegration/WebContent/WEB-INF/Invoice.pdf");
//		} catch (JRException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Done compiling!!! ...");
//	}
//
//}
//
//class DeliveryTypeEnumScopeDeserializer implements
//		JsonDeserializer<DeliveryTypeEnum> {
//
//	@Override
//	public DeliveryTypeEnum deserialize(JsonElement json,
//			java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
//			throws JsonParseException {
//		DeliveryTypeEnum[] scopes = DeliveryTypeEnum.values();
//		for (DeliveryTypeEnum scope : scopes) {
//			if (scope.value().equals(json.getAsString()))
//				return scope;
//		}
//		return null;
//
//	}
//}
//
//class EmailStatusEnumScopeDeserializer implements
//		JsonDeserializer<EmailStatusEnum> {
//
//	@Override
//	public EmailStatusEnum deserialize(JsonElement json,
//			java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
//			throws JsonParseException {
//		EmailStatusEnum[] scopes = EmailStatusEnum.values();
//		for (EmailStatusEnum scope : scopes) {
//			if (scope.value().equals(json.getAsString()))
//				return scope;
//		}
//		return null;
//
//	}
//}
//
//class PrintStatusEnumScopeDeserializer implements
//		JsonDeserializer<PrintStatusEnum> {
//
//	@Override
//	public PrintStatusEnum deserialize(JsonElement json,
//			java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
//			throws JsonParseException {
//		PrintStatusEnum[] scopes = PrintStatusEnum.values();
//		for (PrintStatusEnum scope : scopes) {
//			if (scope.value().equals(json.getAsString()))
//				return scope;
//		}
//		return null;
//
//	}
//}
//
//class ETransactionStatusEnumScopeDeserializer implements
//		JsonDeserializer<ETransactionStatusEnum> {
//
//	@Override
//	public ETransactionStatusEnum deserialize(JsonElement json,
//			java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
//			throws JsonParseException {
//		ETransactionStatusEnum[] scopes = ETransactionStatusEnum.values();
//		for (ETransactionStatusEnum scope : scopes) {
//			if (scope.value().equals(json.getAsString()))
//				return scope;
//		}
//		return null;
//
//	}
//}
//
//class GlobalTaxCalculationEnumEnumScopeDeserializer implements
//		JsonDeserializer<GlobalTaxCalculationEnum> {
//
//	@Override
//	public GlobalTaxCalculationEnum deserialize(JsonElement json,
//			java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
//			throws JsonParseException {
//		GlobalTaxCalculationEnum[] scopes = GlobalTaxCalculationEnum.values();
//		for (GlobalTaxCalculationEnum scope : scopes) {
//			if (scope.value().equals(json.getAsString()))
//				return scope;
//		}
//		return null;
//
//	}
//}
//
//class AttributeScopeDeserializer implements
//		JsonDeserializer<LineDetailTypeEnum> {
//
//	@Override
//	public LineDetailTypeEnum deserialize(JsonElement json,
//			java.lang.reflect.Type arg1, JsonDeserializationContext arg2)
//			throws JsonParseException {
//		LineDetailTypeEnum[] scopes = LineDetailTypeEnum.values();
//		for (LineDetailTypeEnum scope : scopes) {
//			if (scope.value().equals(json.getAsString()))
//				return scope;
//		}
//		return null;
//
//	}
//}
//
//class DateTypeAdapter implements JsonDeserializer<Date> {
//	private final DateFormat format = DateFormat.getInstance();
//
//	public JsonElement serialize(Date src, Type typeOfSrc,
//			JsonSerializationContext context) {
//		String dateFormatAsString = format.format(src);
//		return new JsonPrimitive(dateFormatAsString);
//	}
//
//	public Date deserialize(JsonElement json, Type typeOfT,
//			JsonDeserializationContext context) throws JsonParseException {
//		if (!(json instanceof JsonPrimitive)) {
//			throw new JsonParseException("The date should be a string value");
//		}
//
//		try {
//			return format.parse(json.getAsString());
//		} catch (java.text.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public Date deserialize(JsonElement arg0, java.lang.reflect.Type arg1,
//			JsonDeserializationContext arg2) throws JsonParseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
