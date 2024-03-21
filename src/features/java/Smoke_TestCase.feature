Feature: Smoke TestCase Functionality

  @Demo_RW
  Scenario: Demo TestCase For Guest User playBack Functionality RW
    Given Launching the Application RW
    Then Verify the playback for the Show RW

  @Demo_MRW
  Scenario: Demo TestCase For Guest User playBack Functionality MRW
    Given Launching the Application MRW
    Then Verify the playback for the Show MRW
    
    
   @Demo_Android
  Scenario: Demo TestCase For Guest User playBack Functionality Android
    Given Launching the Application Android
    Then Verify the playback for the Show Android 
    
    
   @Demo_IOS
  Scenario: Demo TestCase For Guest User playBack Functionality IOS
    Given Launching the Application IOS
    Then Verify the playback for the Show IOS  
    
    
   @Demo_AndroidTV
  Scenario: Demo TestCase For Guest User playBack Functionality AndroidTV
    Given Launching the Application AndroidTV
    Then Verify the playback for the Show AndroidTV  
    
   @Demo_LGWEBOSTV
  Scenario: Demo TestCase For Guest User playBack Functionality LGWEBOSTV
    Given As a Anonymous user Navigate to IDP page
    When User Selecting the Watch Now CTA from IDP page
    Then verify the Series content should start playing