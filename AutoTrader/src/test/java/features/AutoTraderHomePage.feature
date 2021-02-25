Feature: Testing for presence of elements

Background:
Given Launch of Auto Trader

@TC_001
Scenario:
Given User Execute Test "TC_001" with description as "Auto Trader Home Page Link Element Check"
Then check if link "lnk_Browser_By_Make" is present
And check if link "lnk_Browse_By_Style" is present
And check if link "lnk_Browse_By_AdvancedSearch" is present

@TC_002
Scenario:
Given User Execute Test "TC_002" with description as "Auto Trader Home Page Search Button Element Check"
Then check if Search button "btn_Search" is present

@TC_003
Scenario:
Given User Execute Test "TC_003" with description as "Auto Trader Home Page Search Dropdown Elements Check"
Then check if Search button "drpDown_SearchForCar" is present
And check if Search button "drpDown_SearchForModel" is present
