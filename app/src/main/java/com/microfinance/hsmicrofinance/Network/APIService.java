package com.microfinance.hsmicrofinance.Network;


import com.microfinance.hsmicrofinance.Network.entity.AccountExtraInfoResponse;
import com.microfinance.hsmicrofinance.Network.entity.AddCommentSupportResponse;
import com.microfinance.hsmicrofinance.Network.entity.BankTransferHistory;
import com.microfinance.hsmicrofinance.Network.entity.Banks;
import com.microfinance.hsmicrofinance.Network.entity.BanksCurrencyDetails;
import com.microfinance.hsmicrofinance.Network.entity.Countries;
import com.microfinance.hsmicrofinance.Network.entity.Currencies;
import com.microfinance.hsmicrofinance.Network.entity.ForgotPasswordResponse;
import com.microfinance.hsmicrofinance.Network.entity.InternalTransferResponse;
import com.microfinance.hsmicrofinance.Network.entity.LoginResponse;
import com.microfinance.hsmicrofinance.Network.entity.OtherBankTransferResponse;
import com.microfinance.hsmicrofinance.Network.entity.PayLoanResponse;
import com.microfinance.hsmicrofinance.Network.entity.PinChangeResponse;
import com.microfinance.hsmicrofinance.Network.entity.SingleSupportItem;
import com.microfinance.hsmicrofinance.Network.entity.SuccessfulTransfer;
import com.microfinance.hsmicrofinance.Network.entity.SupportHistory;
import com.microfinance.hsmicrofinance.Network.entity.SupportResponse;
import com.microfinance.hsmicrofinance.Network.entity.TransferSuccesful;
import com.microfinance.hsmicrofinance.Network.entity.UserRegResponse;
import com.microfinance.hsmicrofinance.Network.entity.UserVerificationResponse;
import com.microfinance.hsmicrofinance.Network.models.AccountOtpConfirmation;
import com.microfinance.hsmicrofinance.Network.models.AccountPasswordChange;
import com.microfinance.hsmicrofinance.Network.models.AccountSettingConfirmation;
import com.microfinance.hsmicrofinance.Network.models.AllUsers;
import com.microfinance.hsmicrofinance.Network.models.BillPayment;
import com.microfinance.hsmicrofinance.Network.models.BillStatement;
import com.microfinance.hsmicrofinance.Network.models.Bills;
import com.microfinance.hsmicrofinance.Network.models.CreditTransferDeposit;
import com.microfinance.hsmicrofinance.Network.models.DepositHistory;
import com.microfinance.hsmicrofinance.Network.models.DetailsForWithdraw;
import com.microfinance.hsmicrofinance.Network.models.EdepositStatement;
import com.microfinance.hsmicrofinance.Network.models.InvestPlans;
import com.microfinance.hsmicrofinance.Network.models.InvestmentHistory;
import com.microfinance.hsmicrofinance.Network.models.InvestmentPlanDepositModel;
import com.microfinance.hsmicrofinance.Network.models.LoanHistories;
import com.microfinance.hsmicrofinance.Network.models.LoanPlans;
import com.microfinance.hsmicrofinance.Network.models.LoanRequest;
import com.microfinance.hsmicrofinance.Network.models.ManualDeposit;
import com.microfinance.hsmicrofinance.Network.models.ManualPaymentDetails;
import com.microfinance.hsmicrofinance.Network.models.MpesaDepositCallBack;
import com.microfinance.hsmicrofinance.Network.models.MpesaResponseBody;
import com.microfinance.hsmicrofinance.Network.models.MpesaStatus;
import com.microfinance.hsmicrofinance.Network.models.PaymentGateWays;
import com.microfinance.hsmicrofinance.Network.models.SubmitCreditDebitPayment;
import com.microfinance.hsmicrofinance.Network.models.TransactionHistory;
import com.microfinance.hsmicrofinance.Network.models.UpdateAccountInfo;
import com.microfinance.hsmicrofinance.Network.models.WithDrawOTPResponse;
import com.microfinance.hsmicrofinance.Network.models.WithdrawDetail;
import com.microfinance.hsmicrofinance.Network.models.WithrawOTPRequest;

import com.google.gson.JsonArray;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface APIService {

    //loan history
    @GET("fixed-deposit-history")
    @Headers("Accept: application/json")
    Call<InvestmentHistory> getAllInvestmentHistory();

    @GET("loan-package")
    @Headers("Accept: application/json")
    Call<LoanPlans> getAllLoanPackages();

    //invest ments packages
    @GET("fixed-deposit-package")
    @Headers("Accept: application/json")
    Call<InvestPlans> getAllInvestmentPackages();

    //transaction history
    @Headers("Accept: application/json")
    @GET("transaction/history")
    Call<TransactionHistory> getAllTransactions();

    //get loan history
    @GET("loan-history")
    @Headers("Accept: application/json")
    Call<LoanHistories> getLoanhistory();

    //get all users
    @GET("get-all-users")
    @Headers("Accept: application/json")
    Call<AllUsers> getAllUsers();

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("user/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    // register user
    @Headers("Accept: application/json")
    @Multipart
    @POST("register")
    Call<UserRegResponse> registerUser(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("phone_number") RequestBody phone_number,
            @Part("kra") RequestBody kra,
            @Part("password") RequestBody password,
            @Part("password_confirmation") RequestBody password_confirmation,
            @Part("image\"; filename=\"kra.png\" ") RequestBody photo,
            @Part("term_condition") RequestBody term_condition,
            @Part("pin") RequestBody pin
    );

    @Headers("Accept: application/json")
    @GET("user/otp")
    Call<UserVerificationResponse> checkUserVerification();


    @POST("fixed-deposit-request/{id}")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    Call<InvestmentPlanDepositModel> postFixedDepositRequest(@Path("id") int id, @Field("amount") String amount);

    //loan request
    @POST("loan-package/store/{id}")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    Call<LoanRequest> requestLoan(@Path("id") int id, @Field("amount") String amount);

    //bill creation
    @POST("bill/create")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    Call<Bills> createBill(
            @Field("email") String email,
            @Field("title") String title,
            @Field("description") String description,
            @Field("amount") String amount);

    // bill statement
    @GET("bill-statement")
    @Headers("Accept: application/json")
    Call<BillStatement> getBillStatements();


    @Headers("Accept: application/json")
    @POST("user/otp/confirmation")
    @FormUrlEncoded
    Call<String> sendOTP(
            @Field("otp") String otp);


    //Deposit history
    @Headers("Accept: application/json")
    @GET("edeposit/history")
    Call<DepositHistory> getDepositHistory();

    //Deposit Statement
    @Headers("Accept: application/json")
    @GET("edeposit/statement")
    Call<EdepositStatement> getEdepositStatement();
//getting countries

    //get Bank Details
    @Headers("Accept: application/json")
    @GET("transfer/otherbank")
    Call<Countries> getCountries();


    //get Bank Details
    @Headers("Accept: application/json")
    @GET("get-other-bank-details")
    Call<Banks> getOtherBankDetails();

    // get Currencies
    @Headers("Accept: application/json")
    @GET("term-details")
    Call<Currencies> getCurrencies();

    //get Bank Transfers
    @Headers("Accept: application/json")
    @GET("others/bank/statement")
    Call<BankTransferHistory> getOtherBankStatement();

    //other bank transfer
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("transfer/otherbank/confirm")
    Call<OtherBankTransferResponse> transferToOtherBanks(

            @Field("bank") int bank,
            @Field("currency") String currency,

            @Field("branch") String branch,
            @Field("account_no") String account_no,
            @Field("account_holder_name") String accountHolderName,
            @Field("amount") double amount
    );
    //transfer/otherbank/confirm-otp

    //get confirm Transfer OTP being sent email of user
    //get confirm Transfer OTP being sent email of user
    @Headers("Accept: application/json")
    @POST("transfer/otherbank/transfer-otp/{currency}/{bankid}/{account_no}/{branch}/{account_holder_name}/{amount}")
    Call<String> getHSOtherBankTransferOTP(


            @Path("currency") String currency,

            @Path("bankid") int bankid,
            @Path("branch") String branch,
            @Path("account_no") String account_no,
            @Path("account_holder_name") String accountHolderName,
            @Path("amount") double amount

    );

    //send confirm Transfer OTP being sent email of user
    @Headers("Accept: application/json")
    @POST("transfer/otherbank/confirm-otp")
    @FormUrlEncoded
    Call<TransferSuccesful> sendHSOtherBankTransferOTP(
            @Field("currency") int currency,
            @Field("otp") String otp,
            @Field("amount") double amount,
            @Field("account_holder_name") String accountHolderName,
            @Field("account_no") String account_no,
            @Field("currency_id") int currency_id,

            @Field("currency_rate") double currency_rate,

            @Field("branch") String branch,
            @Field("bank") String bank,
            @Field("total_charge") String total_charge

    );

    //transfer To HS Account
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("transfer/hsgroup/internal/confirm ")
    Call<InternalTransferResponse> transferToHSAccount(
            @Field("account_no") String account_no,
            @Field("amount") String amount
    );

    //get confirm Transfer OTP being sent email of user
    @Headers("Accept: application/json")
    @GET("transfer/hsgroup/internal/transfer-otp/test1/{amount1}/{account}")
    Call<String> getHSTransferOTP(
            @Path("amount1") int amount,
            @Path("account") String account

    );

    //post otp to server from user
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("transfer/hsgroup/internal/confirm-otp")
    Call<String> sendHSTransferOTP(
            @Field("otp") String otp
    );

    //get Support Items
    @Headers("Accept: application/json")
    @GET("user/get-all-support-requests")
    Call<SupportHistory> getSupportItems();

    //AccountSettingConfirmation
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("account-settings/confirmation")
    Call<AccountSettingConfirmation> confirmAccountPassword(@Field("password") String password);

    //Update account infromation
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("account/info/update")
    Call<UpdateAccountInfo> updateAccountInfo(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone_number") String phone,
            @Field("street") String street,
            @Field("city") String city,
            @Field("state") String state,
            @Field("posta_code") String postalCode,
            @Field("country") String country
    );

    //Change password
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("account-password/update1")
    Call<AccountPasswordChange> changePassword(
            @Field("current_password") String current,
            @Field("password") String password,
            @Field("password_confirmation") String newPassword);


    //Confirm otp to change password on account
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("account-password/otp/confirmation1")
    Call<AccountOtpConfirmation> confirmOtp(@Field("otp") Integer otp, @Field("new_pass") String password);

    //Resend otp for password change confir,atiomn
    @Headers("Accept: application/json")
    @POST("account-password/resend/otp")
    Call<String> resendOtpForAccount();

    // create a  support
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("support")
    Call<SupportResponse> createASupport(
            @Field("title") String title,
            @Field("comment") String comment
    );

    //getSupportId
    @Headers("Accept: application/json")
    @GET("support/{id}")
    Call<SingleSupportItem> getOneSupportItemById(
            @Path("id") int id
    );

    //getSupportId
    @Headers("Accept: application/json")
    @GET("make-AI-ChatBot")
    Call<SingleSupportItem> moreopenAIData(
            @Field("message") String title,
            @Field("engine") String comment
    );


    //Add comment SupportId
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("support/{id}")
    Call<AddCommentSupportResponse> addCommenttoSupportItemById(
            @Path("id") int id, @Field("comment") String comment
    );


    //resetPassword
    @POST("password/forgot-password")
    @FormUrlEncoded
    Call<ForgotPasswordResponse> forgotpassword(
            @Field("email") String email);

    //Edeposit credit deposit
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("edeposit/{id}/check")
    Call<CreditTransferDeposit> makeEdepositViaCreditDebit(
            @Path("id") int id,
            @Field("amount") String amount);

    //SubmitEdepositPayment
    @Headers("Accept: application/json")
    @FormUrlEncoded

    @POST("edeposit/deposit")
    Call<SubmitCreditDebitPayment> submitCreditDebitPayment(
            @Field("amount") double amount,
            @Field("credentials") String credential,
            @Field("type") int type,
            @Field("id") int id);

    //Edeposit Payment gateway
    @Headers("Accept: application/json")
    @GET("all/edeposit/gateways")
    Call<PaymentGateWays> getPaymentGateWays();

    //GetWithdrawal Details
    @Headers("Accept: application/json")
    @GET("transfer/e-currency")
    Call<WithdrawDetail> getWithdrawalDetails();

    //Withdraw
    @Headers("Accept: application/json")
    @POST("transfer/e-currency/check/{id}")
    @FormUrlEncoded
    Call<DetailsForWithdraw> withDraw(@Path("id") int id,
                                      @Field("amount") Double amount,
                                      @Field("currency") int termId);

    //Get otp fro withrwaw
    @Headers("Accept: application/json")
    @GET("transfer/e-currency/otp/request/{amount1}/{account}/{currency}/{id}")
    Call<WithrawOTPRequest> getWithDrawOtp(

            @Path("amount1") int amount,
            @Path("account") String account,
            @Path("currency") int currency,
            @Path("id") int id

    );
    //@todo add path

    //confirm withdraw otp
    @Headers("Accept: application/json")
    @POST("transfer/e-currency/confirm-otp1")
    @FormUrlEncoded
    Call<WithDrawOTPResponse> confirmOTpforWithdraw(
            @Field("otp") String otp,
            @Field("amount") String amount,
            @Field("total_amount") String total_amount,
            @Field("total_charge") String total_charge,
            @Field("account_no") String account_no
    );


    @Headers("Accept: application/json")
    @POST("transfer/e-currency/confirm-otp")
    Call<ResponseBody> validateAccount(BillPayment acc);


    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("ipay/create/bill")
    Call<BillPayment> buyAirtime(
            @Field("sender") String sender,
            @Field("biller_name") String biller_code,
            @Field("amount") String amount,
            @Field("phone") String phone,
            @Field("account") String account
    );

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("edeposit/2/check")
    Call<ResponseBody> eDeposit(
            @Field("amount") String amount,
            @Field("currency") String currency
    );

    //deposit via mpesa
    @Headers("Accept: application/json")
    @POST("mpesa/stk/push")
    @FormUrlEncoded
    Call<MpesaResponseBody> depositMobileMoney(@Field("amount") int amount, @Field("phone_number") String phonenumber);

    //mpesa deopsit history
    @Headers("Accept: application/json")
    @GET("check/mpesa/payment")
    Call<MpesaDepositCallBack> getMpesadepositHistory();

    //mpesa status

    @Headers("Accept: application/json")
    @GET("check/mpesa/status")
    Call<MpesaStatus> getMpesaStatus();


    //manual dep[osit check
    @Headers("Accept: application/json")
    @POST("edeposit/{id}/check")
    @FormUrlEncoded
    Call<ManualPaymentDetails> manualDetailcheck(@Path("id") int id, @Field("amount") double amount);

    //Upload manual deposit details

    @Headers("Accept: application/json")
    @Multipart
    @POST("edeposit/deposit/{id}/{amount}")
    Call<ManualDeposit> manualDepositdetails(
            @Path("id") int id,
            @Path("amount") int amount,
            @Part("comment") RequestBody comment,
            @Part("image\"; filename=\"image.png\" ") RequestBody image,
            @Part("currency") RequestBody currency


    );

    @Headers("Accept: application/json")
    @GET("mpesa/pending/delete/{id}")
    Call<JsonArray> deletePending(@Path("id") int id);


    @Headers("Accept: application/json")
    @GET("loan-return/{id}")
    Call<PayLoanResponse> payLoan(
            @Path("id") int id
    );

    // update pin
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("account/pin/update")
    Call<PinChangeResponse> updatepin(
            @Field("updated_pin") String updated_pin,
            @Field("current_pin") String current_pin
    );

    // update pin on login takes just new pin
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("set-user-pin")
    Call<String> updatepinOnLogin(
            @Field("email") String email,
            @Field("pin") String pin
    );


    @Headers("Accept: application/json")
    @GET("get-user-address")
    Call<AccountExtraInfoResponse> AccountExtraInfoResponse(
    );

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("transfer/hsgroup/internal/confirm-otp2")
    Call<SuccessfulTransfer> sendTransferOTP(
            @Field("otp") String otp,
            @Field("amount") String amount,
            @Field("total_amount") String total_amount,
            @Field("total_charge") String total_charge,
            @Field("account_no") String account_no
    );

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("transfer/otherbank/countrylist")
    Call<BanksCurrencyDetails> getOtherBankDetailsFromCountryId(
            @Field("country_id") String country_id
    );

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("firebase/token/storage")
    Call<ResponseBody> updateFCM(
            @Field("email") String email,
            @Field("fcmtoken") String token
    );

    @Headers("Accept: application/json")
    @GET("flush-session")
    Call<ResponseBody> flushSession();

}
