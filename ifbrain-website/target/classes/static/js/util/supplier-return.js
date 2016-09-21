var SupplierReturn = function() {

	// 供应商API调用地址
	var url = "https://api.sabre.com"; // Production
	url = "https://api.test.sabre.com"; // Sandbox

	// 供应商API授权信息
	var authorization = "Basic VmpFNmRXOTRZalJ1Ym1kbWJIVnJNbXg2WWpwRVJWWkRSVTVVUlZJNlJWaFU6VkZZeU9GUnNkR1k9";

	return {
		// 获取授权信息
		getAccessToken : function(fnCall) {
			$.ajax({
				anync : false,
				"url" : url + "/v1/auth/token",
				"type" : "POST",
				"headers" : {
					"Authorization" : authorization,
					"Content-Type" : "application/x-www-form-urlencoded"
				},
				"data" : "grant_type=client_credentials"
			}).done(fnCall);
		}, 

		// 机票查询（Finds the lowest available priced itineraries based upon a travel date）
		bargainFinderMax : function(date,datetime,returndate,returndatetimeStr, originLocationCode, destinationLocationCode, cabin,callbackDone, callbackFail) {
			// 日期格式化
			if (date.length == 10) {
				date = date + "T00:00:00";
			} else {
				date = date.format("yyyy-MM-dd") + "T00:00:00";
			}
			
			
			if (returndate.length == 10) {
				returndate = returndate + "T00:00:00";
			} else {
				returndate = returndate.format("yyyy-MM-dd") + "T00:00:00";
			}
			// 获取授权信息
			$.ajax({
				"url" : url + "/v1/auth/token",
				"type" : "POST",
				"headers" : {
					"Authorization" : authorization,
					"Content-Type" : "application/x-www-form-urlencoded"
				},
				"data" : "grant_type=client_credentials"
			})
			.done(function(data) {
				var requestData = {
					    "OTA_AirLowFareSearchRQ": {
					        "OriginDestinationInformation": [
					            {
					                "DepartureDateTime": date, //"2015-09-09T12:00:00"
					                "DestinationLocation": {
					                    "LocationCode": destinationLocationCode   //"ISB"
					                }, 
					                "OriginLocation": {
					                    "LocationCode": originLocationCode//"LHR"
					                }, 
					                "RPH": "1", 
					                "DepartureWindow" : datetime,
					                "TPA_Extensions": {
					                    "SegmentType": {
					                        "Code": "O"
					                    }
					                }
					            }, 
					            {
					                "DepartureDateTime": returndate  ,// "2015-09-15T12:00:00", 
					                "DestinationLocation": {
					                    "LocationCode":  originLocationCode//"LHR"
					                }, 
					                "OriginLocation": {
					                    "LocationCode": destinationLocationCode//"ISB"
					                }, 
					                "RPH": "2", 
					                "DepartureWindow" : returndatetimeStr,
					                "TPA_Extensions": {
					                    "SegmentType": {
					                        "Code": "O"
					                    }
					                }
					            }
					        ], 
					        "POS": {
					            "Source": [
					                {
					                    "RequestorID": {
					                        "CompanyName": {
					                            "Code": "TN"
					                        }, 
					                        "ID": "1", 
					                        "Type": "1"
					                    }
					                }
					            ]
					        }, 
					        "TPA_Extensions": {
					            "IntelliSellTransaction": {
					                "RequestType": {
					                    "Name": "50ITINS"
					                }
					            }
					        }, 
					        "Target": "Production", 
					        "TravelPreferences": {
					            "CabinPref": [
					                {
					                    "Cabin": "Y"
					                }
					            ], 
					            "TPA_Extensions": {
					                "NumTripsWithRouting": {
					                    "Number": 10
					                }, 
					                "TripType": {
					                "Value":"Circle"
					                }
					            }
					        }, 
					        "TravelerInfoSummary": {
					            "AirTravelerAvail": [
					                {
					                    "PassengerTypeQuantity": [
					                        {
					                            "Code": "NEG", 
					                            "Quantity": 1
					                        }
					                    ]
					                }
					            ], 
					            "PriceRequestInformation": {
					                "CurrencyCode": "GBP"
					            }
					        }
					    }
					};

				// 查询并获取机票信息
				$.ajax({
					"url" : url + "/v1.8.6/shop/flights?mode=live",
					"type" : "POST",
					"headers" : {
						"Authorization" : "Bearer " + data.access_token,
						"Content-Type" : "application/json"
					},
					data : JSON.stringify(requestData)
				})
				.done(function(data) {
					$.ajax({
						url :  Web.contextPath+"/exchangerate/getCurrentExchangeRate",
						type : "GET"
					}).done(function (exchangeRate) {
						var vehicleInfos = new Array();

						
						// 获取机票价格信息
						var pricedItineraries = data.OTA_AirLowFareSearchRS.PricedItineraries.PricedItinerary;
						for (var i = 0; i < pricedItineraries.length; ++i) {
							var pricedItinerary = pricedItineraries[i];
							
							
							var DirectionInd=pricedItinerary.AirItinerary.DirectionInd;
							
							var originDestinationOption = pricedItinerary.AirItinerary.OriginDestinationOptions.OriginDestinationOption;
							
				     for(var k = 0; k < originDestinationOption.length; ++k){
							var elapsedTime = originDestinationOption[k].ElapsedTime;
							var flightSegments = originDestinationOption[k].FlightSegment;
							var str=JSON.stringify(flightSegments);
							
							
							var lengthSeg = flightSegments.length;
							
							
							//中转次数减1
							var resultLengthSeg=lengthSeg-1;
							//飞行时间结合
							var stopElapsedTimeList=new Array();

							var flightSegment = flightSegments[0];
							var departureDateTime = flightSegment.DepartureDateTime.replace("T", " ");
							var startAirport = flightSegment.DepartureAirport.LocationCode;
							var departureTerminalId = flightSegment.DepartureAirport.TerminalID;
							var airline = flightSegment.OperatingAirline.Code;
							var flightNumber = flightSegment.FlightNumber;
							var airEquipType = flightSegment.Equipment[0].AirEquipType;

							var airItineraryPricingInfo = pricedItinerary.AirItineraryPricingInfo[0];
							var priceUSD = airItineraryPricingInfo.ItinTotalFare.TotalFare.Amount;
							var cabin = airItineraryPricingInfo.FareInfos.FareInfo[0].TPA_Extensions.Cabin.Cabin;

							var arrivalDateTime = "";
							var arriveAirport = "";
							var arrivalTerminalId = "";
						
							
							
							// 获取机票价格信息
							var transitInfo = "";
							
							var planInfos=new Array();
                          
							if (lengthSeg == 1) {
								arrivalDateTime = flightSegment.ArrivalDateTime.replace("T", " ");
								arriveAirport = flightSegment.ArrivalAirport.LocationCode;
								arrivalTerminalId = flightSegment.ArrivalAirport.TerminalID;
								var stopAirLine = flightSegment.OperatingAirline.Code;
								var stopFlightNumber = flightSegment.FlightNumber;
								var stopEquipType = flightSegment.Equipment[0].AirEquipType;
								var stopDepartureAirport = flightSegment.DepartureAirport.LocationCode;
								var stopDepartureDateTime = flightSegment.DepartureDateTime.replace("T", " ");
								var stopArrivalAirport = flightSegment.ArrivalAirport.LocationCode;
								var stopArrivalDateTime = flightSegment.ArrivalDateTime.replace("T", " ");
								var stopElapsedTime = flightSegment.ElapsedTime;
								var departureTerminalId = flightSegment.DepartureAirport.TerminalID;
								var stopMin=0;
								var planInfo={
										stopAirLine:stopAirLine,
										stopFlightNumber:stopFlightNumber,
										stopEquipType:stopEquipType,
										stopDepartureAirport:stopDepartureAirport,
										stopArrivalAirport:stopArrivalAirport,
										stopElapsedTime:stopElapsedTime,
										stopCabin:cabin,
										stopDepartureDateTime:stopDepartureDateTime,
										stopArrivalDateTime:stopArrivalDateTime,
										departureTerminalId:departureTerminalId,
										arrivalTerminalId:arrivalTerminalId,
										stopMin:0
								};
								planInfos.push(planInfo);
								
							} else {
								var lastFlightSegment = flightSegments[lengthSeg - 1];

								arrivalDateTime = lastFlightSegment.ArrivalDateTime.replace("T", " ");
								arriveAirport = lastFlightSegment.ArrivalAirport.LocationCode;
								arrivalTerminalId = lastFlightSegment.ArrivalAirport.TerminalID;

								//transitInfo += "  ( ";
								for (var j = 0; j < lengthSeg; ++j) {
									var stopAirLine = flightSegments[j].OperatingAirline.Code;
									var stopFlightNumber = flightSegments[j].FlightNumber;
									var stopEquipType = flightSegments[j].Equipment[0].AirEquipType;
									var stopDepartureAirport = flightSegments[j].DepartureAirport.LocationCode;
									var stopDepartureDateTime = flightSegments[j].DepartureDateTime.replace("T", " ");
									var stopArrivalAirport = flightSegments[j].ArrivalAirport.LocationCode;
									var stopArrivalDateTime = flightSegments[j].ArrivalDateTime.replace("T", " ");
									var stopElapsedTime = flightSegments[j].ElapsedTime;
									var	stopCabin = airItineraryPricingInfo.FareInfos.FareInfo[j].TPA_Extensions.Cabin.Cabin;
									var departureTerminalId = flightSegments[j].DepartureAirport.TerminalID;
									
								
									var arrivalTerminalId=flightSegments[j].ArrivalAirport.TerminalID;
									
									
									/*transitInfo += stopAirLine + stopFlightNumber + "/ " + stopEquipType + " " + stopDepartureAirport + " "
									+ stopDepartureDateTime.substr(11, 5) + " - " + stopArrivalAirport + " " +  stopArrivalDateTime.substr(11, 5)
									+ "Travel " + stopElapsedTime + "  " + stopCabin;*/
                                      
									//把飞行时间添加到。飞行时间数组中
									stopElapsedTimeList[j]=stopElapsedTime;
									
									var stopMin;
									if (j < lengthSeg -1) {
									var nextFlyTime = flightSegments[j + 1].DepartureDateTime.replace("T", " ");
									 stopMin = (Date.parse(nextFlyTime) - Date.parse(stopArrivalDateTime)) / (60 * 1000);

										/*transitInfo += 	" Lay " + stopMin;
										transitInfo += "; "*/
									}
									
									var planInfo={
											stopAirLine:stopAirLine,
											stopFlightNumber:stopFlightNumber,
											stopEquipType:stopEquipType,
											stopDepartureAirport:stopDepartureAirport,
											stopArrivalAirport:stopArrivalAirport,
											stopElapsedTime:stopElapsedTime,
											stopCabin:stopCabin,
											stopMin:stopMin,
											stopDepartureDateTime:stopDepartureDateTime,
											stopArrivalDateTime:stopArrivalDateTime,
											departureTerminalId:departureTerminalId,
											arrivalTerminalId:arrivalTerminalId
									}
									planInfos[j]=planInfo;
									
								}
							
								//transitInfo += ")";
							}
					
							var priceCNY = parseInt((priceUSD * exchangeRate * 100).toString()) / 100;
							/*var summary = departureDateTime.substr(11, 5) + " - " + arrivalDateTime.substr(11, 5)
									+ ",  "  + startAirport +" - "+ arriveAirport +",  " + cabin + ",  $" + priceUSD + transitInfo;*/
							/*var summary=planInfos;*/
							
							var vehicleInfo = {
								DirectionInd:DirectionInd,
								summary: planInfos,
								departureTime: departureDateTime,
								arrivalTime: arrivalDateTime,
								airline: airline,
								vehicleNumber: flightNumber,
								costPrice: priceUSD,
								rank: cabin,
								startAirport: startAirport,
								arriveAirport: arriveAirport,
								departureTerminalId: departureTerminalId,
								arrivalTerminalId: arrivalTerminalId,
								airEquipType: airEquipType,
								lengthSeg: resultLengthSeg,
								stopElapsedTimeList:stopElapsedTimeList,
								elapsedTime:elapsedTime
							};
							vehicleInfos.push(vehicleInfo);
				 	}
						}
						callbackDone(vehicleInfos);
					}).fail(function() { alert("获取汇率信息失败!"); });
				}).fail(function(){alert("机票获取失败")}); //callbackFail
			}).fail(callbackFail);
		},
		
		// 酒店查询
		searchHotel : function (date, locationCode, callbackDone, callbackFail) {
			// TODO: 调用酒店提供商API接口，获取酒店房间信息（参照上面的机票查询）
			
			// TODO: 此处为测试数据，待酒店提供商API接口可用后，删除即可
			var hotelRoomInfos = [{
				summary: "盘古大酒店，五星级，$300.00",
				hotelName : "盘古大酒店",
				hotelRank: "五星级",
				hotelCostPrice: 300.00,
				hotelAddress: "北京市北四环中路",
				hotelTel: "010-62345678"
			}, {
				summary: "香格里拉酒店，五星级，$500.00",
				hotelName : "香格里拉酒店",
				hotelRank: "五星级",
				hotelCostPrice: 500.00,
				hotelAddress: "XX市XX路",
				hotelTel: "010-87654321"
			}];
			callbackDone(hotelRoomInfos);
		}
	};
}();
