
# Final Project "VetSystem"

## For this cource I decided to create application which could be used in Veterinary clinic. I was asked to create GUI based on use case scenario from documentation I have prepared earlier. This part contains adding visit to database. 

 ## I have choosen :
  * ### javaFx for GUI part of app because of its "simplicity". 
  * ### spring boot because of JPA repositories (even though I didn't create web app)  and dependency injection. 
  
 #### Joining javaFx with spring boot wasn't easy, fxWeaver library [https://github.com/rgielen/javafx-weaver] enable such process. 

### Main Window with values  
![Alt text](https://lh3.googleusercontent.com/qjKrHng4m4EeqfWK-Oi0x8kUJuKnA2SlfkQ3C1FPO4slg3gEaVxRsq5plCU_XWsybi2XLXla6J7QFUmZc9sdR3wx0X0BiaQIF3BK8LmxvUmaKqiVIbNvpsXvORzNPpR8TWByZeZUJzWTu6Ad-3vhagjSd6CqNGOzZB-dTFryfeKJxaMTeHHzfy7WlfPX6UgvXNIKhVQ0nw3_fa06jD4433ixUeGl5p8KHewiKUCBMiTRThW_4eCZBP_2jr6kdWRw2v2-NL7vwa96CsFdf6La4WVyjZ7TV_abIBLOT0jxQFmCMBjGR5fc-KcdhBkRDy17wMGM2ZNMtYku8rx54nY9OOFMsL4jgjZoup5bl8Hz8B_vrlwd8s6rdx7NbcTg_tBpF2A5DY0VxGAwB_5OmVLF2SNJC7pl98isac7D3sb2BDLg5OxtH5D-GUWCBncfT4y6p0L_0LdPy5LmnzY0qTP02j9Ik7nGTYrXvB_pEyD7YBKROLXHLDRL5DmsyKGcmw4ub9_ZoOwvzTIau4kWGnZaqhpYh9MW65xQTHXUqkflBOr-5VnJCpN1Hy2J1IB-fiexQPAJ0f8CFxMccYV4NWJ4taj2YssjOHh97qbwGjZyfciW6c46i-VhAqDsYtgYGZjWUzc0fBLsLp2y81W9IDrunG_g41UR1y3vrRflowJh8GIYs08ODcIg46Wiv_sdm4qOeluU0nw16aUtLTa73uwme3o=w553-h318-no?authuser=2)

### if Doctor is not working on this day

![Alt text](https://lh3.googleusercontent.com/b5-TITjKa_LqKbxGiLLqmB6ERQJBYmM1t5UX00B9N5NIx-hDi-sXLM0Um1_QXkX9IChcoK4YvSDiGd9rNfEePKbtTTQNtnwcYLudFVBLhuQhTuiBPatahJh86ULw7IYr3BDSKusAJbYCGctepDAYRwtgRQ3Yij_F7pGhOfp_dNENTQkPdVHRzoUaydMdyAXFzeOHMh9M9G9bkX5zijv_SN1kUov1WORo39i4jLpCciq9XmlTKoVhEMTFhFTNMEcDZTHiNQGkyi6itKT86NNxXltOxLGD7Co39TxR1aDkMuvx8yy2XIwkhUShdKM4M3jTAcWQFSQU43z8TdYyg6SfC4SvILTxlTqIxdazTKH1edcnJVNOBPRYlJUtJhPJ5f8zmriyEDP3duyLYAbxZLR-52_fghMymgGvhdb_f5t0zonlFd-OQt0rtadFCljBMD8X__5rM_Lzrkavy84pAmDo42GKzfNs1fUEVxlizBD7J4pjwHoPFIWQFn6eF2ijrzFPNOOMDH4mY5uPIz3PtqsC01iQbUbVVFt_QbhwI3IAn-k45w2Cyqx6E9f_bGpqZOdMoB-f7QlQuzG79xtVZAFp6-MYK7_Hdsk7CFK5IDpr1n2nHssbnr7zwIJXjjcnjkw3XW4e_cHLBcurley-r-MDsBqYUGvzn41tcAI4sVpHiqNJ5cajagv-Bjl-zHqeXs-KDbM12oz4cjf31DhAcCMRSxs=w553-h317-no?authuser=2)

### if Doctor is "booked" (no available hours left)

![Alt text](https://lh3.googleusercontent.com/cVT0qGxBXagTRzQANhwZVcoMALVTQUtWApWtOnK246B_rD6evZTPAZ1h3l4lxZY606ru6bJHhHdd2_fOVa4avtNpu8G2nsR3J0RZiqrFj7AvdHrzwhHA5nR-7cs01ntwSw_FyQRN82-otGuYsRVXwGYuStXsXcTLyZcyaqDBLWJsUhN11LDvD68PPsIzHRImFd9NrtOlQ1ZC1JYBdUS1i1MbMHu7UnE2kzeoNqPb9yKXTnvKzS5ALaW45eTjMsRLvRhigHe6t5W8khWj5srH3ZRvL6n1PMuNVyTviHh5wX1QsEsKdvlypWgH8JlEcbPD5VioB7K6aYrbP_Y1Waf0pDc7TfFL8SrfEE5sA4GJSuOjmzjoxk6LTKwhabyIrQ8CwWFivlkfYO5FVkd4aal9rHo-VOhb1Bkv7uUvkAkX6kwLPR89v-yIMPNiE6jE6pZY_ZSCbz4nHGbVqHxXmTelhdqFWW8LPx4uAFQJtJCi4EdcQ6pWDXJzChm09lMqstJfdsWDC3a43vUvVE84NsRILEIrM45nMcVDD7fkT1_o6TC_tLQVMzUDNNOZnIqGMJ9YpX6TzV56PBQAyzH2WVzMVUbWELzLnyB1NTNYNlMy1qNEuItEvKpfYTliPrH2DUS3KGgRMBbC6tg96ojdeNF8ytt4RylSCveQ6R3Sx5DZV5XFEqn-GabxRrHHDCInpyQImjQLxMQ88M026VxjJLuSh7o=w553-h319-no?authuser=2)

### adding visit confirmation with additional info in table

![Alt text](https://lh3.googleusercontent.com/bUKrTLproWf9e5DYsseiz1KUDIFJ-zUEpEqcayLNvJnfQiWeawjHCcNOQlDOYlFDGYQdRbqRjplvDjKUE2bQC6BvIEeO1B42aW2ZhSHM-H_aSjEnKJiR60TL2I9pxdYg6tqGMDiPI1CwLZk7fyQ84B9e41VLahaHvHPMPoo1eOjjXBjxXix0YahBtFpg-Te3I2SeBMjJdq_1Y7R6ML155zVxyItD3K-QnLFz2UcZa5PzXb1FESxArqLeYxBuaasC00kugOvX2SmnKFU0L7L3fWPXdF_hNOFa-cdBBzDuWptD9HoabzgyUE1jt2oq0LtOd9KCgjKv11ZyijaUH4KyMaAY-GJfZoOzf68kvJXApOqprEh6RV_oXLehGDbifRbrVd0Y-8mwr7X_7prWeD9FoHwFt56ZCxiIK4piTLmbvIpx0-31GQHEI-fj6yN9RgRdrycFRYWigGEaSohmyRZipvob1GDwmAPU-ATX0pH5uHy1eSpGCnVkpbUC9ynaZLWlBgLsEv9e1qlTpqa2jHBgER7Kde8HTYRALn7ghFmOoh0Jlsx_tbvNg5dW1Z5tgm65FPkyN3xreCGKbJszejuTYov0_SMz2Eh1_8L52X4TPiyrbj_Qm7DL4GUFdlAGLWkxtRrCnL4g-23bxMn2EitGlc94HQ7nXHdvrsuUojeOa3oYTVUL2HEMR6_WuWTD1E6MFihaFKEK1jwHCXaCRDTFFPQ=w553-h317-no?authuser=2)

## Tech Stack

* ### Language
  * #### Java 15
    * ##### Project Lombok
    * ##### JavaFx
    * ##### JavaFx-Weaver[https://github.com/rgielen/javafx-weaver]
* ### Frameworks : 
  * #### Hibernate
  * #### Spring 5
  * #### Spring Boot 2 
* ### Database
  * #### H2 database
* ### Tools
  * #### Maven
  * #### Flyway



### in the near future I am planning to change the type of application from desktopApp to webApp
