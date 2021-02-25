Feature: Validating advanced search feature for car sale

Background:
Given Launch of Auto Trader
When Navigated to the advanced Serach page
Then check if text box "txt_Zipcode" is present
And check if label "lbl_condition" is present
And check if checkbox "chk_Certified" is present
And check if label "lbl_Style" is present
And check if checkbox "chk_Convertible" is present
And check if label "lbl_Year" is present
And check if dropdown "drpDown_FromYear" is present
And check if dropdown "drpDown_ToYear" is present
And check if label "lbl_MakeModelAndTrim" is present
And check if dropdown "drpDown_Make" is present
And check if search button "btn_SearchAdvanced" is present


@TC_004
Scenario:
Given User Execute Test "TC_004" with description as "Selecting the Advaced Search Criteria"
When enter "30004" in "txt_ZipCode"
And check the checkbox "chk_Certified"
And check the checkbox "chk_Convertible"
And select "2017" from drop down "drpDown_FromYear"
And select "2020" from drop down "drpDown_ToYear"
And select "BMW" from drop down "drpDown_Make"
And click button "btn_SearchAdvanced"
Then user should be able to see only "BMW" listings

