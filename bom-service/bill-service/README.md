# bulls-for-home-accounting-2ver

#actuator from zuul
localhost:8011/bills-service/actuator/
#create bill
localhost:8011/bills-service/bill

{
	"userUuid":"42be74e6-dfc8-4406-8405-a273a4dd3bd2",
	"billName": "bill number two",
	"startSum":25.59,
	"description":"test-bill",
	"billNumber":6
}

#create category
localhost:8011/bills-service/category

{
	"userUuid":"42be74e6-dfc8-4406-8405-a273a4dd3bd2",
	"categoryName": "category number two",
	"type":"OUTCOME",
	"decryption":"test-category"
}
#create operation
localhost:8011/bills-service/operation

{
	"userUuid":"42be74e6-dfc8-4406-8405-a273a4dd3bd2",
	"billUuid":"13295df5-0adb-4aa9-926c-9ece8346d104",
	"categoryUuid":"a62e6979-adb9-44e7-bc15-e61f2d1cc570",
	"sum":4.0,
	"currency":"RUB",
	"type":"OUTCOME"
}

