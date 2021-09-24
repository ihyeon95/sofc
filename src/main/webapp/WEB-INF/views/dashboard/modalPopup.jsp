<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- table border -->
<style>
  table.border {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  table.border th, table.border td {
    border: 1px solid #444444;
  }
  td.greenback {
  	background: #28a745;
  }
  
  input.textback {
  	
  	background-color: #F3EEFF;
  	width: 50%;
  	padding-top: 1px;
  	padding-bottom: 1px;
  	margin-left: auto;
  	margin-right: auto;
  	text-align: center;
  }
  
  input.textback1 {
  	background-color: #F3EEFF;
  }
  
  input.btnWidth {
  	width: 90%;
  }
  
  input.btnWidth1 {
  	width: 15%;
  }
  
  span {
  	line-height : 45px
  }
  
</style>

<!-- 계측값 -->
<div class="modal fade bd-example-modal-lg" id="measurementsModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">계측값</h3>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="form-row">
					<div class="col-md-3 mb-3">
						<span>(전)전압</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mDCVoltage" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>V</span>
					</div>
					<div class="col-md-3 mb-3">
						<span>누적생산열량</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mAccumulateHeatProduce" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>kWh</span>
					</div>
				</div>
	
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<span>(전)전류</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mDCAmpare" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>A</span>
					</div>
					<div class="col-md-3 mb-3">
						<span>누적사용열량</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mAccumulateHeatConsume" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>kWh</span>
					</div>
				</div>
	
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<span>(전)출력</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mDCWatt" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>W</span>
					</div>
					<div class="col-md-3 mb-3">
						<span>전일누적사용열량</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mPreDayAccumulateHeatProduce" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>kWh</span>
					</div>
				</div>
	
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<span>(후)전압</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mRearACVoltage" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>V</span>
					</div>
					<div class="col-md-3 mb-3">
						<span>급수온도</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mInletTemp" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>℃</span>
					</div>
				</div>
	
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<span>(후)전류</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mRearACAmpare" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>A</span>
					</div>
					<div class="col-md-3 mb-3">
						<span>온수출구온도</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mOutletTemp" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>℃</span>
					</div>
				</div>
	
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<span>(후)출력</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mRearACWatt" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>W</span>
					</div>
					<div class="col-md-3 mb-3">
						<span>역률</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mPowerFactor" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>%</span>
					</div>
				</div>
	
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<span>생산열량</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mHeatProduce" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>W</span>
					</div>
					<div class="col-md-3 mb-3">
						<span>주파수</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mFreq" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>Hz</span>
					</div>
				</div>
	
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<span>고장여부</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mStatusOpONOFF" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3"></div>
					<div class="col-md-3 mb-3">
						<span>누적 발전량</span>
					</div>
					<div class="col-md-2 mb-3">
						<input id="mAccumulateWattProduce" class="form-control" type="text" value="" readonly>
					</div>
					<div class="col-md-1 mb-3">
						<span>Wh</span>
					</div>
				</div>
				
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<span>최신 데이터 획득 시간</span>
			   			<span><div id="mtimestamp"></div></span>
					</div>
				</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<!-- 시뮬레이션(에러 생성) -->
<div class="modal fade" id="simulation">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">시뮬레이션(에러 생성)</h3>
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
			</div>
			<div class="modal-body">
				<form id="simulationFrm">
					<input type="hidden" name="rtuId" value="${iRtuNum}">
					<input type="hidden" name="iBdNum" value="${iBdNum}">
					
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<span>에러 선택</span>
						</div>
						<div class="col-md-4 mb-3">
							<span>PT1</span>
						</div>
						<div class="col-md-4 mb-3">
							<span>TC02</span>
						</div>
					</div>
						
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<select class="textback form-control" name="errorNo">
								<option value="1">error1</option>
								<option value="2">error2</option>
								<option value="3">error3</option>
								<option value="4">error4</option>
								<option value="5">error5</option>
								<option value="6">error6</option>
								<option value="7">error7</option>
								<option value="8">error8</option>
								<option value="9">error9</option>
								<option value="10">error10</option>
								<option value="11">error11</option>
								<option value="12">error12</option>
								<option value="13">error13</option>
								<option value="14">error14</option>
								<option value="15">error15</option>
								<option value="16">error16</option>
								<option value="17">error17</option>
								<option value="18">error18</option>
							</select>
						</div>
						<div class="col-md-4 mb-3">
							<input class="textback1 form-control" type="text" value="0" name="pt1">
						</div>
						<div class="col-md-4 mb-3">
							<input class="textback1 form-control" type="text" value="0" name="tc02">
						</div>
					</div>
					
					<div class="form-row">
						<div class="col-md-4 mb-3"></div>
						<div class="col-md-4 mb-3">
							<input class="form-control" type="button" value="모드 진입" onclick="insertSimulatorMode('1', ${iRtuNum}, ${iBdNum});">
						</div>
						<div class="col-md-4 mb-3">
							<input class="form-control" type="button" value="모드 해제" onclick="insertSimulatorMode('0', ${iRtuNum}, ${iBdNum});">
						</div>
					</div>
					
					<div class="form-row">
						<div class="col-md-4 mb-3"></div>
						<div class="col-md-4 mb-3">
							<input class="form-control" type="button" value="시뮬레이션 적용" onclick="insertSimulatorActive()">
						</div>
						<div class="col-md-4 mb-3">
							<input class="form-control" type="button" value="취소"  onclick="$('#simulation').modal('hide')">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 파라메터 변경 -->
<div class="modal fade bd-example-modal-lg" id="parameters">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">파라메터 변경</h3>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
            </div>
            <form id="parametersForm">
            	<input type="hidden" name="rtuId" value="${iRtuNum}">
				<input type="hidden" name="iBdNum" value="${iBdNum}">
				
	            <div class="modal-body">
	                <div class="form-row">
	                	<div class="col-md-4 mb-3">
	                		<input type="checkbox" value="1" name="pump1Select"/> 
	                		<span>개질용 도시가스 펌프 유량값</span>
	                	</div>
	                	<div class="col-md-5 mb-3">
	                		<input type="text" class="textback1" value="" name="pump1Value" />
	                		<span>0.00 ~ 5.00</span>
	                	</div>
	                	<div class="col-md-2 mb-3">
	                		<input type="checkbox" value="1" name="sol1Select"/>
	                		<span>SOL1</span>
	                	</div>
	                	<div class="col-md-1 mb-3">
	                		<!-- <input type="button" value="OFF" onclick="fncOnOffBtn(this);"/> -->
	                		<input class="btn btn-secondary btn-xs btnWidth" type="button" value="OFF" onclick="fncOnOffBtn(this);">
	                		<input type="hidden" value="0" class="btnValue" name="sol1Value"/>
	                	</div>
	                </div>
	                
	                <div class="form-row">
	                	<div class="col-md-4 mb-3">
	                		<input type="checkbox" value="1" name="pump2Select"/> 
	                		<span>버너용 도시가서 펌프 유량값</span>
	                	</div>
	                	<div class="col-md-5 mb-3">
	                		<input type="text"  class="textback1" value="" name="pump2Value" />
	                		<span>0.00 ~ 5.00</span>
	                	</div>
	                	<div class="col-md-2 mb-3">
	                		<input type="checkbox" value="1" name="sol2Select"/>
	                		<span>SOL2</span>
	                	</div>
	                	<div class="col-md-1 mb-3">
	                		<!-- <input type="button" value="OFF" onclick="fncOnOffBtn(this);"/> -->
	                		<input class="btn btn-secondary btn-xs btnWidth" type="button" value="OFF" onclick="fncOnOffBtn(this);">
	                		<input type="hidden" value="0" class="btnValue" name="sol2Value"/>
	                	</div>
	                </div>
	                
	                <div class="form-row">
	                	<div class="col-md-4 mb-3">
	                		<input type="checkbox" value="1" name="pump3Select"/> 
	                		<span>공기 블로워 유량값</span>
	                	</div>
	                	<div class="col-md-5 mb-3">
	                		<input type="text" class="textback1" value="" name="pump3Value" />
	                		<span>0 ~ 150</span>
	                	</div>
	                	<div class="col-md-2 mb-3">
	                		<input type="checkbox" value="1" name="sol4Select"/>
	                		<span>SOL4</span>
	                	</div>
	                	<div class="col-md-1 mb-3">
	                		<!-- <input type="button" value="OFF" onclick="fncOnOffBtn(this);"/> -->
	                		<input class="btn btn-secondary btn-xs btnWidth" type="button" value="OFF" onclick="fncOnOffBtn(this);">
	                		<input type="hidden" value="0" class="btnValue" name="sol4Value"/>
	                	</div>
	                </div>
	                
	                <div class="form-row">
	                	<div class="col-md-4 mb-3">
	                		<input type="checkbox" value="1" name="pump4Select"/> 
	                		<span>워터펌프 유량값</span>
	                	</div>
	                	<div class="col-md-5 mb-3">
	                		<input type="text" class="textback1" value="" name="pump4Value" />
	                		<span>0.0 ~ 15.0</span>
	                	</div>
	                	<div class="col-md-2 mb-3">
	                		<input type="checkbox" value="1" name="sol5Select"/>
	                		<span>SOL5</span>
	                	</div>
	                	<div class="col-md-1 mb-3">
	                		<!-- <input type="button" value="OFF" onclick="fncOnOffBtn(this);"/> -->
	                		<input class="btn btn-secondary btn-xs btnWidth" type="button" value="OFF" onclick="fncOnOffBtn(this);">
	                		<input type="hidden" value="0" class="btnValue" name="sol5Value"/>
	                	</div>
	                </div>
	                
	                <div class="form-row">
	                	<div class="col-md-4 mb-3">
	                		<input type="checkbox" value="1" name="pcsSelect"/> 
	                		<span>인버터 전류지령값</span>
	                	</div>
	                	<div class="col-md-5 mb-3">
	                		<input type="text" class="textback1" value="" name="pcsValue" />
	                		<span>0.0 ~ 25.0</span>
	                	</div>
	                	<div class="col-md-2 mb-3">
	                		<input type="checkbox" value="1" name="sol6Select"/>
	                		<span>SOL6</span>
	                	</div>
	                	<div class="col-md-1 mb-3">
	                		<!-- <input type="button" value="OFF" onclick="fncOnOffBtn(this);"/> -->
	                		<input class="btn btn-secondary btn-xs btnWidth" type="button" value="OFF" onclick="fncOnOffBtn(this);">
	                		<input type="hidden" value="0" class="btnValue" name="sol6Value"/>
	                	</div>
	                </div>
	                
	                <div class="form-row">
	                	<div class="col-md-4 mb-3">
	                		<input type="checkbox" value="1" name="snhSelect"/> 
	                		<span>점화용 세라믹 히터</span>
	                	</div>
	                	<div class="col-md-5 mb-3">
	                		<!-- <input type="button" value="OFF" onclick="fncOnOffBtn(this);"/> -->
	                		<input class="btn btn-secondary btn-xs btnWidth1" type="button" value="OFF" onclick="fncOnOffBtn(this);">
	                		<input type="hidden" value="0" class="btnValue" name="snhValue" />
	                	</div>
	                	<div class="col-md-2 mb-3">
	                		<input type="checkbox" value="1" name="threeway1Select"/>
	                		<span>3WAY1</span>
	                	</div>
	                	<div class="col-md-1 mb-3">
	                		<!-- <input type="button" value="OFF" onclick="fncOnOffBtn(this);"/> -->
	                		<input class="btn btn-secondary btn-xs btnWidth" type="button" value="OFF" onclick="fncOnOffBtn(this);">
	                		<input type="hidden" value="0" class="btnValue" name="threeway1Value"/>
	                	</div>
	                </div>
	            </div>
	        </form>
            <div class="modal-footer">
            	<button type="button" class="btn btn-secondary" onclick="insertParameters('0',${iRtuNum},${iBdNum});">변경</button>
            	<button type="button" class="btn btn-secondary" onclick="insertParameters('1',${iRtuNum},${iBdNum});">정상값 복귀</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
            </div>
        </div>
    </div>
</div>

<!-- 정확도 보정 -->
<div class="modal fade bd-example-modal-lg" id="accuracy">
    <div class="modal-dialog modal-lg" style="max-width:1280px">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">정확도 보정</h3>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
            </div>
            <div class="modal-body">
            	<form id="gainOffsetFrm">
            		<input type="hidden" name="rtuId" value="${iRtuNum}">
					<input type="hidden" name="iBdNum" value="${iBdNum}">
					
		            <div class="row">
		            	<div class="col-3">
							<div class="according card">
								<div class="card-body">
									<h4 class="header-title mb-1">PUMP 1</h4>
									<div class="row">
										<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input gain" id="gain1">
						            			<label class="form-check-label" for="gain1">Gain</label>
				            					<input type="hidden" value="0" name="gain"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="gainValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(소수점 두자리)</label>
					            		</div>
					            		<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input offset" id="offset1">
						            			<label class="form-check-label" for="offset1">Offset</label>
				            					<input type="hidden" value="0" name="offset"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-5.00 ~ 5.00)</label>
					            		</div>
				            		</div>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">PUMP 2</h4>
									<div class="row">
										<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input gain" id="gain2">
						            			<label class="form-check-label" for="gain2">Gain</label>
				            					<input type="hidden" value="0" name="gain"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="gainValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(소수점 두자리)</label>
					            		</div>
					            		<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input offset" id="offset2">
						            			<label class="form-check-label" for="offset2">Offset</label>
				            					<input type="hidden" value="0" name="offset"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-5.00 ~ 5.00)</label>
					            		</div>
				            		</div>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">PUMP 3</h4>
									<div class="row">
										<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input gain" id="gain3">
						            			<label class="form-check-label" for="gain3">Gain</label>
				            					<input type="hidden" value="0" name="gain"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="gainValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(소수점 두자리)</label>
					            		</div>
					            		<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input offset" id="offset3">
						            			<label class="form-check-label" for="offset3">Offset</label>
				            					<input type="hidden" value="0" name="offset"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-200 ~ 200)</label>
					            		</div>
				            		</div>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">PUMP 4</h4>
									<div class="row">
										<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input gain" id="gain4">
						            			<label class="form-check-label" for="gain4">Gain</label>
												<input type="hidden" value="0" name="gain"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="gainValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(소수점 두자리)</label>
					            		</div>
					            		<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input offset" id="offset4">
						            			<label class="form-check-label" for="offset4">Offset</label>
												<input type="hidden" value="0" name="offset"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-20.0 ~ 20.0)</label>
					            		</div>
				            		</div>
								</div>
							</div>
						</div>
						<div class="col-3">
							<div class="according card">
								<div class="card-body">
									<h4 class="header-title mb-1">FL 1</h4>
									<div class="row">
										<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input gain" id="gain5">
						            			<label class="form-check-label" for="gain5">Gain</label>
												<input type="hidden" value="0" name="gain"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="gainValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(소수점 두자리)</label>
					            		</div>
					            		<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input offset" id="offset5">
						            			<label class="form-check-label" for="offset5">Offset</label>
												<input type="hidden" value="0" name="offset"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-5.00 ~ 5.00)</label>
					            		</div>
				            		</div>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">FL 2</h4>
									<div class="row">
										<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input gain" id="gain6">
						            			<label class="form-check-label" for="gain6">Gain</label>
												<input type="hidden" value="0" name="gain"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="gainValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(소수점 두자리)</label>
					            		</div>
					            		<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input offset" id="offset6">
						            			<label class="form-check-label" for="offset6">Offset</label>
												<input type="hidden" value="0" name="offset"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-5.00 ~ 5.00)</label>
					            		</div>
				            		</div>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">FL 3</h4>
									<div class="row">
										<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input gain" id="gain7">
						            			<label class="form-check-label" for="gain7">Gain</label>
												<input type="hidden" value="0" name="gain"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="gainValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(소수점 두자리)</label>
					            		</div>
					            		<div class="col-6">
											<div class="form-check">
												<input type="checkbox" class="form-check-input offset" id="offset7">
						            			<label class="form-check-label" for="offset7">Offset</label>
												<input type="hidden" value="0" name="offset"/>
											</div>
						            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
						            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-200 ~ 200)</label>
					            		</div>
				            		</div>
								</div>
							</div>
						</div>
						<div class="col-2">
							<div class="according card">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 1</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset8">
				            			<label class="form-check-label" for="offset8">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
			            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 2</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset9">
				            			<label class="form-check-label" for="offset9">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 3</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset10">
				            			<label class="form-check-label" for="offset10">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 4</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset11">
				            			<label class="form-check-label" for="offset11">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 5</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset12">
				            			<label class="form-check-label" for="offset12">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 6</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset13">
				            			<label class="form-check-label" for="offset13">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
						</div>
						<div class="col-2">
							<div class="according card">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 7</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset14">
				            			<label class="form-check-label" for="offset14">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 8</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset15">
				            			<label class="form-check-label" for="offset15">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 9</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset16">
				            			<label class="form-check-label" for="offset16">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 10</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset17">
				            			<label class="form-check-label" for="offset17">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
							<div class="according card mt-1">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 11</h4>
									<div class="form-check">
										<input type="checkbox" class="form-check-input offset" id="offset18">
				            			<label class="form-check-label" for="offset18">Offset</label>
										<input type="hidden" value="0" name="offset"/>
									</div>
				            		<input type="text" class="textback1 form-control" name="offsetValue" value="0"/>
				            		<label class="mb-0" style="font-size: 12px;color: #616161;text-align: center;width: 100%;">(-999 ~ 999)</label>
								</div>
							</div>
						</div>
					</form>
					<div class="col-2">
						<form id="kalmanFrm">
							<input type="hidden" name="rtuId" value="${iRtuNum}">
							<input type="hidden" name="iBdNum" value="${iBdNum}">
					
							<div class="according card">
								<div class="card-body">
									<h4 class="header-title mb-1">TC 필터</h4>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc1">
				            				<label class="form-check-label" for="tc1">tc1</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc2">
				            				<label class="form-check-label" for="tc2">tc2</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc3">
				            				<label class="form-check-label" for="tc3">tc3</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc4">
				            				<label class="form-check-label" for="tc4">tc4</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc5">
				            				<label class="form-check-label" for="tc5">tc5</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc6">
				            				<label class="form-check-label" for="tc6">tc6</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc7">
				            				<label class="form-check-label" for="tc7">tc7</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc8">
				            				<label class="form-check-label" for="tc8">tc8</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc9">
				            				<label class="form-check-label" for="tc9">tc9</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc10">
				            				<label class="form-check-label" for="tc10">tc10</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
									<div class="row mt-4">
										<div class="form-check col-6 ml-4">
											<input type="checkbox" class="form-check-input tc" id="tc11">
				            				<label class="form-check-label" for="tc11">tc11</label>
											<input type="hidden" value="0" name="tc"/>
										</div>
										<div style="text-align: right;">
				            				<input class="btn btn-secondary btn-xs btn-block" style="width:45px;" type="button" value="OFF" onclick="fncOnOffBtn(this);">
				            				<input type="hidden" value="0" class="btnValue" name="tcValue"/>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
            </div>
            <div class="modal-footer">
            	<div class="col-10">
	            	<button type="button" class="btn btn-secondary" onclick="insertGainOffset();">변경</button>
	            	<button type="button" class="btn btn-secondary" onclick="getGainOffset();">초기화</button>
	            	<button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
            	</div>
            	<div style="text-align: right;" class="col-2">
	            	<button type="button" class="btn btn-secondary" onclick="alert('TC필터는 변경할 수 없습니다.');">변경</button>
            	</div>
            </div>
        </div>
    </div>
</div>

<!-- 착화 공정 -->
<div class="modal fade" id="ignition">
	<div class="modal-dialog modal-lg" style="max-width:1280px">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">착화공정</h3>
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
			</div>
			<div class="modal-body">
				<form id="igniteFrm">
					<input type="hidden" name="rtuId" value="${iRtuNum}">
					<input type="hidden" name="iBdNum" value="${iBdNum}">
					
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th>품목</th>
								<th>조건1</th>
								<th>조건2</th>
								<th>조건3</th>
								<th>조건4</th>
								<th>목표값</th>
								<th>구분</th>
							</tr>
						</thead>
						<tbody class="ignition">
							<tr>
								<td>PUMP3</td>
								<td>운전버튼 인가시<input type="hidden" class="textback form-control" value="0" name="condition1"></td>
								<td></td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition3"></td>
								<td></td>
								<td><input type="text" class="textback form-control" value="0" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>SOL4</td>
								<td>운전버튼 인가시</td>
								<td></td>
								<td></td>
								<td></td>
								<td>ON</td>
								<td>(ON/OFF)</td>
							</tr>
							<tr>
								<td>SNH</td>
								<td>운전버튼 인가</td>
								<td>20</td>
								<td>초 후</td>
								<td></td>
								<td>ON</td>
								<td>(ON/OFF)</td>
							</tr>
							<tr>
								<td>SOL2</td>
								<td>SNH 작동</td>
								<td>60</td>
								<td>초 후</td>
								<td></td>
								<td>ON</td>
								<td>(ON/OFF)</td>
							</tr>
							<tr>
								<td>PUMP2</td>
								<td>SNH 작동<input type="hidden" class="textback form-control" value="0" name="condition1"></td>
								<td>5</td>
								<td>초 후<input type="hidden" class="textback form-control" value="0" name="condition3"></td>
								<td></td>
								<td><input type="text" class="textback form-control" value="0.00" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>SNH</td>
								<td>SOL2 작동</td>
								<td>10</td>
								<td>초 후</td>
								<td></td>
								<td>OFF</td>
								<td>(ON/OFF)</td>
							</tr>
							<tr>
								<td>화염감지</td>
								<td><input type="text" class="textback form-control" value="0" name="condition1"></td>
								<td>초 후</td>
								<td><input type="text" class="textback form-control" value="0" name="condition3"></td>
								<td>℃</td>
								<td>ON<input type="hidden" class="textback form-control" value="0" name="targetValue"></td>
								<td>(ON/OFF)</td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-secondary" onclick="javascript:insertIgnite();" >적용</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
            </div>
		</div>
	</div>
</div>

<!-- 승온 공정 -->
<div class="modal fade" id="temperature">
	<div class="modal-dialog modal-lg" style="max-width:1280px">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">승온공정 - 제어 로직</h3>
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
			</div>
			<div class="modal-body">
				<form id="heatingFrm">
					<input type="hidden" name="rtuId" value="${iRtuNum}">
					<input type="hidden" name="iBdNum" value="${iBdNum}">
					
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th>품목</th>
								<th>조건1</th>
								<th>조건2</th>
								<th>속도값</th>
								<th>목표값</th>
								<th>구분</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>PUMP3</td>
								<td>공정 전환 즉시</td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>SOL1</td>
								<td>공정 전환 즉시</td>
								<td></td>
								<td></td>
								<td>ON</td>
								<td>(ON/OFF)</td>
							</tr>
							<tr>
								<td>PUMP1</td>
								<td>SOL1 작동 후</td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0.00" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.00" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>PUMP2</td>
								<td>공정 전환 즉시</td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0.00" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.00" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>PUMP4</td>
								<td>TC 02 >=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(ccm)</td>
							</tr>
							<tr>
								<td>PUMP1</td>
								<td>TC 01 >=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0.00" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.00" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>PUMP4</td>
								<td>TC 01 >=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(ccm)</td>
							</tr>
							<tr>
								<td>PUMP2</td>
								<td>TC 01 >=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="hidden" class="textback form-control" value="0.00" name="speedValue"></td>
								<td>OFF<input type="hidden" class="textback form-control" value="0.00" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>SOL2</td>
								<td>PUMP2 =</td>
								<td>0</td>
								<td></td>
								<td>OFF</td>
								<td>(ON/OFF)</td>
							</tr>
							<tr>
								<td>PUMP3</td>
								<td>TC 01 >=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0" name="targetValue"></td>
								<td>(LPM)</td>
							</tr>
							<tr>
								<td>PCS</td>
								<td>TC 01 >=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0" name="targetValue"></td>
								<td>(A/Pac)</td>
							</tr>
							<tr>
								<td>PUMP4</td>
								<td>TC 02 <=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0" name="targetValue"></td>
								<td>(ccm)</td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-secondary" onclick="insertHeating()">적용</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
            </div>
		</div>
	</div>
</div>

<!-- 운전 공정 -->
<div class="modal fade" id="controll">
	<div class="modal-dialog modal-lg" style="max-width:1280px">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">운전공정 - 제어 로직</h3>
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
			</div>
			<div class="modal-body">
				<table class="table table-bordered text-center">
					<thead>
						<tr>
							<th>품목</th>
							<th>조건1</th>
							<th>조건2</th>
							<th>조건3</th>
							<th>속도값</th>
							<th>목표값</th>
							<th>구분</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>PUMP1</td>
							<td></td>
							<td>PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0" ></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS&lt;=</td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>&lt;PCS</td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><= TC01 or TC10</td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>-(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>>= TC01 or TC10</td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>+(LPM/LPM)</td>
						</tr>

						<tr>
							<td>PUMP3</td>
							<td></td>
							<td>TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(LPM/LPM)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01 &lt;=</td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(LPM/LPM)</td>
						</tr>
						
						<tr>
							<td>PUMP4</td>
							<td></td>
							<td>TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>(LPM/ccm)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>(LPM/ccm)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>(LPM/ccm)</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01</td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>(LPM/ccm)</td>
						</tr>
						
						<tr>
							<td>PCS</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>&lt;TC 01</td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td>0.0</td>
							<td>(A/A)</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(A /Pac)</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td>v &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td><input type="text" class="textback form-control" value="0.0"></td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td>(A /Vdc)</td>
						</tr>
						<tr>
							<td>PUMP2</td>
							<td></td>
							<td>TC 01 &lt;</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM)</td>
						</tr>
						<tr>
							<td>PUMP2</td>
							<td></td>
							<td>TC 01 &lt;</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td></td>
							<td>OFF</td>
							<td></td>
						</tr>
						<tr>
							<td>SNH</td>
							<td></td>
							<td>PUMP2 OFF 1분 후</td>
							<td></td>
							<td></td>
							<td>ON</td>
							<td></td>
						</tr>
						<tr>
							<td>PUMP2</td>
							<td></td>
							<td>SNH 작동 5초 후</td>
							<td></td>
							<td></td>
							<td><input type="text" class="textback form-control" value="0.00"></td>
							<td>(LPM)</td>
						</tr>
						<tr>
							<td>SOL2</td>
							<td></td>
							<td>SOL2 작동 5초 후</td>
							<td></td>
							<td></td>
							<td>ON</td>
							<td>(LPM)</td>
						</tr>
						<tr>
							<td>SNH</td>
							<td></td>
							<td>SOL2 작동 1분 후</td>
							<td></td>
							<td></td>
							<td>OFF</td>
							<td>(LPM)</td>
						</tr>
						<tr>
							<td>PUMP2</td>
							<td></td>
							<td>TC 01 &lt;=</td>
							<td><input type="text" class="textback form-control" value="0"></td>
							<td></td>
							<td>OFF</td>
							<td>℃</td>
						</tr>
						<tr>
							<td>SOL2</td>
							<td></td>
							<td>PUMP2 OFF</td>
							<td></td>
							<td></td>
							<td>OFF</td>
							<td>℃</td>
						</tr>
					</tbody>
				</table>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-secondary" onclick="insertControll(${iRtuNum},${iBdNum});">적용</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
            </div>
		</div>
	</div>
</div>

<!-- 종료/저탕조 공정 -->
<div class="modal fade" id="exit">
	<div class="modal-dialog modal-lg" style="max-width:1280px">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">종료 - 제어로직</h3>
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
			</div>
			<div class="modal-body">
				<form id="endWaterFrm">
					<input type="hidden" name="rtuId" value="${iRtuNum}">
					<input type="hidden" name="iBdNum" value="${iBdNum}">
					
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th>품목</th>
								<th>조건1</th>
								<th>조건2</th>
								<th>조건3</th>
								<th>속도값</th>
								<th>목표값</th>
								<th>구분</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>PCS</td>
								<td>종료 명령 입력</td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>0.0<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(A,A)</td>
							</tr>
							<tr>
								<td>PUMP1</td>
								<td>PCS전류</td>
								<td>0<input type="hidden" class="textback form-control" value="0" name="condition2"></td>
								<td>A도달시</td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(LPM,LPM)</td>
							</tr>
							<tr>
								<td>PUMP4</td>
								<td>PCS전류</td>
								<td>0<input type="hidden" class="textback form-control" value="0" name="condition2"></td>
								<td>A도달시</td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(ccm,ccm)</td>
							</tr>
							<tr>
								<td>PUMP3</td>
								<td>PCS전류</td>
								<td>0<input type="hidden" class="textback form-control" value="0" name="condition2"></td>
								<td>A도달시</td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(LPM,LPM)</td>
							</tr>
							<tr>
								<td>PUMP4</td>
								<td>TC 02 &lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td><input type="text" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(ccm,ccm)</td>
							</tr>
							<tr>
								<td>PUMP4</td>
								<td>TC 02 &lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>0.0<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(ccm,ccm)</td>
							</tr>
							<tr>
								<td>PUMP1</td>
								<td>TC 01 &lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>0.0<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(LPM,LPM)</td>
							</tr>
							<tr>
								<td>SOL1</td>
								<td>TC 01 &lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td>-<input type="hidden" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>OFF<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(ON/OFF)</td>
							</tr>
							<tr>
								<td>PUMP3</td>
								<td>TC 01 &lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td><input type="text" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>0<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
								<td>(LPM,LPM)</td>
							</tr>
							<tr>
								<td>발전시작</td>
								<td>TC 01 &gt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td><input type="hidden" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>운전진입<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
								<td></td>
							</tr>
						</tbody>
					</table>
					<br><span>저탕조 - 제어 로직</span>
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th>품목</th>
								<th>조건1</th>
								<th>조건2</th>
								<th>속도값</th>
								<th>목표값</th>
								<th>구분</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>SOL6</td>
								<td>TC 11 &gt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td>ON<input type="hidden" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>(ON/OFF)<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
							</tr>
							<tr>
								<td>SOL6</td>
								<td>TC 11 &lt;</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td>OFF<input type="hidden" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>(ON/OFF)<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
							</tr>
							<tr>
								<td>3WAY1</td>
								<td>TC 11 &gt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td>A<input type="hidden" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>저탕조<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
							</tr>
							<tr>
								<td></td>
								<td>TC 11 &lt;</td>
								<td><input type="text" class="textback form-control" value="0" name="condition2"></td>
								<td></td>
								<td>B<input type="hidden" class="textback form-control" value="0.0" name="speedValue"></td>
								<td>보일러<input type="hidden" class="textback form-control" value="0.0" name="targetValue"></td>
							</tr>
						</tbody>
					</table>
				</form>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-secondary" onclick="insertEndWater()">적용</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
            </div>
		</div>
	</div>
</div>

<!-- 에러 조건 -->
<div class="modal fade" id="error">
	<div class="modal-dialog modal-lg" style="max-width:1280px">
		<div class="modal-content">
			<div class="modal-header">
				<h3 class="modal-title">에러 조건 - 제어 로직</h3>
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
			</div>
			<div class="modal-body">
				<form id="errorFrm">
					<input type="hidden" name="rtuId" value="${iRtuNum}">
					<input type="hidden" name="iBdNum" value="${iBdNum}">
					
					<table class="table table-bordered text-center">
						<thead>
							<tr>
								<th>품목</th>
								<th>조건1</th>
								<th>조건2</th>
								<th>조건3</th>
								<th>시간(sec)</th>
								<th>TC3</th>
								<th>구분</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Error 02</td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition1"></td>
								<td>TC&lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition3"></td>
								<td><input type="text" class="textback form-control" value="0" name="sec"></td>
								<td><input type="text" class="textback form-control" value="0" name="tc3"></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="text" class="textback form-control" value="0" name="condition1"></td>
								<td>TC&lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition3"></td>
								<td><input type="text" class="textback form-control" value="0" name="sec"></td>
								<td><input type="text" class="textback form-control" value="0" name="tc3"></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="text" class="textback form-control" value="0" name="condition1"></td>
								<td>TC&lt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition3"></td>
								<td><input type="text" class="textback form-control" value="0" name="sec"></td>
								<td><input type="text" class="textback form-control" value="0" name="tc3"></td>
								<td></td>
							</tr>
							<tr>
								<td>Error 15</td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition1"></td>
								<td>PT&gt;=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition3"></td>
								<td>pa<input type="hidden" class="textback form-control" value="0" name="sec"></td>
								<td><input type="hidden" class="textback form-control" value="0" name="tc3"></td>
								<td></td>
							</tr>
							<tr>
								<td>Error 08</td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition1"></td>
								<td>TC01 >=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition3"></td>
								<td>°C<input type="hidden" class="textback form-control" value="0" name="sec"></td>
								<td><input type="hidden" class="textback form-control" value="0" name="tc3"></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="hidden" class="textback form-control" value="0" name="condition1"></td>
								<td>TC01 <=</td>
								<td><input type="text" class="textback form-control" value="0" name="condition3"></td>
								<td>°C<input type="hidden" class="textback form-control" value="0" name="sec"></td>
								<td><input type="hidden" class="textback form-control" value="0" name="tc3"></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="modal-footer">
            	<button type="button" class="btn btn-secondary" onclick="insertError()">적용</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
            </div>
		</div>
	</div>
</div>
<script>

// 정확도 보정 좌측 gain, offset 변경
function insertGainOffset(){
	$.ajax({
		url : '/systemCont/insertGain'
		,data : $("#gainOffsetFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,async : false
		,success : function(data){
			if(data.res1 == null){
				alert("Gain  데이터 전송에 실패했습니다.");
			}else{
				alert("Gain 정상처리 되었습니다.");
			} 
			
			setGain(data.res1);
			
			$.ajax({
				url : '/systemCont/insertOffset'
				,data : $("#gainOffsetFrm").serialize()
				,dataType : 'json'
				,type : 'post'				
				,async : false
				,success : function(data){
					if(data.res2 == null){
						alert("Offset 데이터 전송에 실패했습니다.");
					}else{
						alert("Offset 정상처리 되었습니다.");
					}
					setOffset(data.res2);
				}
			    ,beforeSend : function(xhr) {
			        xhr.setRequestHeader("AJAX", true);
			        //-TODO : LOADING IMG
			    }
			    ,error : function(xhr,status,error) {
			        //-TODO : LOADING IMG 제거
			        /* if(xhr.status == 401) {
			            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
			            location.href = "/";
			        } else if(xhr.status == 403) {
			            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
			            location.href = "/";
			        } else {
			        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
			            location.href = "/";
			            //alert("["+xhr.status+"]오류입니다.\n");
			    		return;	
			        } */
			    }
			})
			
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
	
	
}

function getGainOffset(){
	$("#gainOffsetFrm")[0].reset()
	$.ajax({
		url : '/systemCont/getGain'
		,data : $("#gainOffsetFrm").serialize()
		,dataType : 'json'
		,type : 'post'			
		,async : false
		,success : function(data){
			if(data.res1 == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			}
			setGain(data.res1);
			
			$.ajax({
				url : '/systemCont/getOffset'
				,data : $("#gainOffsetFrm").serialize()
				,dataType : 'json'
				,type : 'post'			
				,async : false
				,success : function(data){
					if(data.res2 == null){
						alert("조회에 실패했습니다.");
						// TODO modal close
					}
					setOffset(data.res2);
					
					// 칼만 조회 시 주석 제외할것.
					// 소켓통신으로 동시에 2개의 connect 유지 시 데이터가 꼬이는 문제 발생. gain, offset 조회 후 조회하도록 처리
					// getKalman();
				}
			    ,beforeSend : function(xhr) {
			        xhr.setRequestHeader("AJAX", true);
			        //-TODO : LOADING IMG
			    }
			    ,error : function(xhr,status,error) {
			        //-TODO : LOADING IMG 제거
			        /* if(xhr.status == 401) {
			            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
			            location.href = "/";
			        } else if(xhr.status == 403) {
			            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
			            location.href = "/";
			        } else {
			        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
			            location.href = "/";
			            //alert("["+xhr.status+"]오류입니다.\n");
			    		return;	
			        } */
			    }
			})
			
			// 칼만 조회 시 주석 제외할것.
			// 소켓통신으로 동시에 2개의 connect 유지 시 데이터가 꼬이는 문제 발생. gain, offset 조회 후 조회하도록 처리
			// getKalman();
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
	
	
}

function setGain(gain){
	if(gain == null) return;
	//console.log(gain);
	//console.log(offset);

	$("[name=gainValue]")[0].value = gain.pump1.value/100;
	$("[name=gain]")[0].value == 100 ?$(".form-check-input.gain")[0].click():"";
	$("[name=gainValue]")[1].value = gain.pump2.value/100;
	$("[name=gain]")[1].value == 100 ?$(".form-check-input.gain")[1].click():"";
	$("[name=gainValue]")[2].value = gain.pump3.value/100;
	$("[name=gain]")[2].value == 100 ?$(".form-check-input.gain")[2].click():"";
	$("[name=gainValue]")[3].value = gain.pump4.value/100;
	$("[name=gain]")[3].value == 100 ?$(".form-check-input.gain")[3].click():"";
	$("[name=gainValue]")[4].value = gain.fl1.value/100;
	$("[name=gain]")[4].value == 100 ?$(".form-check-input.gain")[4].click():"";
	$("[name=gainValue]")[5].value = gain.fl2.value/100;
	$("[name=gain]")[5].value == 100 ?$(".form-check-input.gain")[5].click():"";
	$("[name=gainValue]")[6].value = gain.fl3.value/100;
	$("[name=gain]")[6].value == 100 ?$(".form-check-input.gain")[6].click():"";
}

function setOffset(offset){
	if(offset == null) return;
	//console.log(gain);
	//console.log(offset);

	$("[name=offsetValue]")[0].value = offset.pump1.value/100;
	offset.pump1.sign == -56 ? $("[name=offsetValue]")[0].value = $("[name=offsetValue]")[0].value*-1:"";
	$("[name=offset]")[0].value == 100 ?$(".form-check-input.offset")[0].click():"";
	$("[name=offsetValue]")[1].value = offset.pump2.value/100;
	offset.pump2.sign == -56 ? $("[name=offsetValue]")[1].value = $("[name=offsetValue]")[1].value*-1:"";
	$("[name=offset]")[1].value == 100 ?$(".form-check-input.offset")[1].click():"";
	$("[name=offsetValue]")[2].value = offset.pump3.value/100;
	offset.pump3.sign == -56 ? $("[name=offsetValue]")[2].value = $("[name=offsetValue]")[2].value*-1:"";
	$("[name=offset]")[2].value == 100 ?$(".form-check-input.offset")[2].click():"";
	$("[name=offsetValue]")[3].value = offset.pump4.value/100;
	offset.pump4.sign == -56 ? $("[name=offsetValue]")[3].value = $("[name=offsetValue]")[3].value*-1:"";
	$("[name=offset]")[3].value == 100 ?$(".form-check-input.offset")[3].click():"";
	$("[name=offsetValue]")[4].value = offset.fl1.value/100;
	offset.fl1.sign == -56 ? $("[name=offsetValue]")[4].value = $("[name=offsetValue]")[4].value*-1:"";
	$("[name=offset]")[4].value == 100 ?$(".form-check-input.offset")[4].click():"";
	$("[name=offsetValue]")[5].value = offset.fl2.value/100;
	offset.fl2.sign == -56 ? $("[name=offsetValue]")[5].value = $("[name=offsetValue]")[5].value*-1:"";
	$("[name=offset]")[5].value == 100 ?$(".form-check-input.offset")[5].click():"";
	$("[name=offsetValue]")[6].value = offset.fl3.value/100;
	offset.fl3.sign == -56 ? $("[name=offsetValue]")[6].value = $("[name=offsetValue]")[6].value*-1:"";
	$("[name=offset]")[6].value == 100 ?$(".form-check-input.offset")[6].click():"";

	$("[name=offsetValue]")[7].value = offset.tc1.value/100;
	offset.tc1.sign == -56 ? $("[name=offsetValue]")[7].value = $("[name=offsetValue]")[7].value*-1:"";
	$("[name=offset]")[7].value == 100 ?$(".form-check-input.offset")[7].click():"";
	$("[name=offsetValue]")[8].value = offset.tc2.value/100;
	offset.tc2.sign == -56 ? $("[name=offsetValue]")[8].value = $("[name=offsetValue]")[8].value*-1:"";
	$("[name=offset]")[8].value == 100 ?$(".form-check-input.offset")[8].click():"";
	$("[name=offsetValue]")[9].value = offset.tc3.value/100;
	offset.tc3.sign == -56 ? $("[name=offsetValue]")[9].value = $("[name=offsetValue]")[9].value*-1:"";
	$("[name=offset]")[9].value == 100 ?$(".form-check-input.offset")[9].click():"";
	$("[name=offsetValue]")[10].value = offset.tc4.value/100;
	offset.tc4.sign == -56 ? $("[name=offsetValue]")[10].value = $("[name=offsetValue]")[10].value*-1:"";
	$("[name=offset]")[10].value == 100 ?$(".form-check-input.offset")[10].click():"";
	$("[name=offsetValue]")[11].value = offset.tc5.value/100;
	offset.tc5.sign == -56 ? $("[name=offsetValue]")[11].value = $("[name=offsetValue]")[11].value*-1:"";
	$("[name=offset]")[11].value == 100 ?$(".form-check-input.offset")[11].click():"";
	$("[name=offsetValue]")[12].value = offset.tc6.value/100;
	offset.tc6.sign == -56 ? $("[name=offsetValue]")[12].value = $("[name=offsetValue]")[12].value*-1:"";
	$("[name=offset]")[12].value == 100 ?$(".form-check-input.offset")[12].click():"";
	$("[name=offsetValue]")[13].value = offset.tc7.value/100;
	offset.tc7.sign == -56 ? $("[name=offsetValue]")[13].value = $("[name=offsetValue]")[13].value*-1:"";
	$("[name=offset]")[13].value == 100 ?$(".form-check-input.offset")[13].click():"";
	$("[name=offsetValue]")[14].value = offset.tc8.value/100;
	offset.tc8.sign == -56 ? $("[name=offsetValue]")[14].value = $("[name=offsetValue]")[14].value*-1:"";
	$("[name=offset]")[14].value == 100 ?$(".form-check-input.offset")[14].click():"";
	$("[name=offsetValue]")[15].value = offset.tc9.value/100;
	offset.tc9.sign == -56 ? $("[name=offsetValue]")[15].value = $("[name=offsetValue]")[15].value*-1:"";
	$("[name=offset]")[15].value == 100 ?$(".form-check-input.offset")[15].click():"";
	$("[name=offsetValue]")[16].value = offset.tc10.value/100;
	offset.tc10.sign == -56 ? $("[name=offsetValue]")[16].value = $("[name=offsetValue]")[16].value*-1:"";
	$("[name=offset]")[16].value == 100 ?$(".form-check-input.offset")[16].click():"";
	$("[name=offsetValue]")[17].value = offset.tc11.value/100;
	offset.tc11.sign == -56 ? $("[name=offsetValue]")[17].value = $("[name=offsetValue]")[17].value*-1:"";
	$("[name=offset]")[17].value == 100 ?$(".form-check-input.offset")[17].click():"";
	
}

// 정확도 보정 우측 TC필터 수정(kalman)
function insertKalman(){
	$.ajax({
		url : '/systemCont/insertKalman'
		,data : $("#kalmanFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
			}
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

function getKalman(){
	$.ajax({
		url : '/systemCont/getKalman'
		,data : $("#kalmanFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}
			setKalman(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

function setKalman(data){
	if(data == null) return;
	//console.log(data);
	
}

//착화공정 수정
function insertIgnite(){
	
	$.ajax({
		url : '/systemCont/insertIgnite'
		,data : $("#igniteFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
			}
			setHeating(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

//착화공정 조회
function getIgniting(){
	
	$.ajax({
		url : '/systemCont/igniteInfo'
		,data : $("#igniteFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			}
			setIgnite(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

//착화공정 세팅
function setIgnite(data){
	
	var inputArray = $("#igniteFrm").find("input");
	
	inputArray[2].value = data.pump3.condition1/100.0;
	inputArray[3].value = data.pump3.condition3/100.0;
	inputArray[4].value = data.pump3.targetValue/100.0;	
	
	inputArray[5].value = data.pump2.condition1/100.0;
	inputArray[6].value = data.pump2.condition3/100.0;
	inputArray[7].value = data.pump2.targetValue/100.0;	
	
	inputArray[8].value = data.snh.condition1/100.0;
	inputArray[9].value = data.snh.condition3/100.0;
	inputArray[10].value = data.snh.targetValue/100.0;	
	
}

//승온공정 수정
function insertHeating(){
	
	$.ajax({
		url : '/systemCont/insertHeating'
		,data : $("#heatingFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
			}
			setHeating(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

//승온공정 조회
function getHeating(){
	
	$.ajax({
		url : '/systemCont/getHeating'
		,data : $("#heatingFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			}
			setHeating(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

//승온공정 세팅
function setHeating(data){
	//console.log(data);
	var inputArray = $("#heatingFrm").find("input");
	
	inputArray[2].value = data.pump3_1.condition2/100.0;
	inputArray[3].value = data.pump3_1.speedValue/100.0;
	inputArray[4].value = data.pump3_1.targetValue/100.0;	
	
	inputArray[5].value = data.pump1_1.condition2/100.0;
	inputArray[6].value = data.pump1_1.speedValue/100.0;
	inputArray[7].value = data.pump1_1.targetValue/100.0;	
	
	inputArray[8].value = data.pump2_1.condition2/100.0;
	inputArray[9].value = data.pump2_1.speedValue/100.0;
	inputArray[10].value = data.pump2_1.targetValue/100.0;	
	
	inputArray[11].value = data.pump4_1.condition2/100.0;
	inputArray[12].value = data.pump4_1.speedValue/100.0;
	inputArray[13].value = data.pump4_1.targetValue/100.0;
	
	inputArray[14].value = data.pump1_2.condition2/100.0;
	inputArray[15].value = data.pump1_2.speedValue/100.0;
	inputArray[16].value = data.pump1_2.targetValue/100.0;
	
	inputArray[17].value = data.pump4_2.condition2/100.0;
	inputArray[18].value = data.pump4_2.speedValue/100.0;
	inputArray[19].value = data.pump4_2.targetValue/100.0;
	
	inputArray[20].value = data.pump2_2.condition2/100.0;
	inputArray[21].value = data.pump2_2.speedValue/100
	inputArray[22].value = data.pump2_2.targetValue/100.0;
	
	inputArray[23].value = data.pump3_2.condition2/100.0;
	inputArray[24].value = data.pump3_2.speedValue/100.0;
	inputArray[25].value = data.pump3_2.targetValue/100.0;
	
	inputArray[26].value = data.pcs.condition2/100.0;
	inputArray[27].value = data.pcs.speedValue/100.0;
	inputArray[28].value = data.pcs.targetValue/100.0;

	inputArray[29].value = data.pump4_3.condition2/100.0;
	inputArray[30].value = data.pump4_3.speedValue/100.0;
	inputArray[31].value = data.pump4_3.targetValue/100.0;
}

// 시뮬레이션 모드진입/해제
function insertSimulatorMode( mode, rtuId, iBdNum ){
	$.ajax({
		url : '/systemCont/insertSimulatorMode'
		,data : {
					mode:mode,
					rtuId:rtuId,
					iBdNum:iBdNum
				}
		,dataType : 'json'
		,type : 'post'
		,success : function(data){
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
			}
			//console.log(data);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

// 시뮬레이션 적용
function insertSimulatorActive(){
	$.ajax({
		url : '/systemCont/insertSimulatorActive'
		,data : $("#simulationFrm").serialize()
		,dataType : 'json'
		,type : 'post'
		,success : function(data){
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
			}
			//console.log(data);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

//종료/저탕조 수정
function insertEndWater(){
	
	$.ajax({
		url : '/systemCont/insertEndWater'
		,data : $("#endWaterFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
			}
			setEndWater(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}


//승온공정 조회
function getEndWater(){
	
	$.ajax({
		url : '/systemCont/getEndWater'
		,data : $("#endWaterFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			}
			setEndWater(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

function setEndWater(data){
	//console.log(data);
	var inputArray = $("#endWaterFrm").find("input");
	
	inputArray[2].value = data.pcs.condition2/100.0;
	inputArray[3].value = data.pcs.speedValue/100.0;
	inputArray[4].value = data.pcs.targetValue/100.0;	
	
	inputArray[5].value = data.pump1_1.condition2/100.0;
	inputArray[6].value = data.pump1_1.speedValue/100.0;
	inputArray[7].value = data.pump1_1.targetValue/100.0;	
	
	inputArray[8].value = data.pump4_1.condition2/100.0;
	inputArray[9].value = data.pump4_1.speedValue/100.0;
	inputArray[10].value = data.pump4_1.targetValue/100.0;	
	
	inputArray[11].value = data.pump3_1.condition2/100.0;
	inputArray[12].value = data.pump3_1.speedValue/100.0;
	inputArray[13].value = data.pump3_1.targetValue/100.0;
	
	inputArray[14].value = data.pump4_2.condition2/100.0;
	inputArray[15].value = data.pump4_2.speedValue/100.0;
	inputArray[16].value = data.pump4_2.targetValue/100.0;
	
	inputArray[17].value = data.pump4_3.condition2/100.0;
	inputArray[18].value = data.pump4_3.speedValue/100.0;
	inputArray[19].value = data.pump4_3.targetValue/100.0;
	
	inputArray[20].value = data.pump1_2.condition2/100.0;
	inputArray[21].value = data.pump1_2.speedValue/100
	inputArray[22].value = data.pump1_2.targetValue/100.0;
	
	inputArray[23].value = data.sol1.condition2/100.0;
	inputArray[24].value = data.sol1.speedValue/100.0;
	inputArray[25].value = data.sol1.targetValue/100.0;

	inputArray[26].value = data.pump3_2.condition2/100.0;
	inputArray[27].value = data.pump3_2.speedValue/100.0;
	inputArray[28].value = data.pump3_2.targetValue/100.0;

	inputArray[29].value = data.start.condition2/100.0;
	inputArray[30].value = data.start.speedValue/100.0;
	inputArray[31].value = data.start.targetValue/100.0;

	inputArray[32].value = data.sol6_1.condition2/100.0;
	inputArray[33].value = data.sol6_1.speedValue/100.0;
	inputArray[34].value = data.sol6_1.targetValue/100.0;

	inputArray[35].value = data.sol6_2.condition2/100.0;
	inputArray[36].value = data.sol6_2.speedValue/100.0;
	inputArray[37].value = data.sol6_2.targetValue/100.0;

	inputArray[38].value = data.threeway1_1.condition2/100.0;
	inputArray[39].value = data.threeway1_1.speedValue/100.0;
	inputArray[40].value = data.threeway1_1.targetValue/100.0;

	inputArray[41].value = data.threeway1_2.condition2/100.0;
	inputArray[42].value = data.threeway1_2.speedValue/100.0;
	inputArray[43].value = data.threeway1_2.targetValue/100.0;
}

//종료/저탕조 수정
function insertError(){
	
	$.ajax({
		url : '/systemCont/insertError'
		,data : $("#errorFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			//console.log(data);
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
				// 성공했을 경우 정보 세팅
				//setError(data.res);
			}
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

//승온공정 조회
function getError(){
	
	$.ajax({
		url : '/systemCont/getError'
		,data : $("#errorFrm").serialize()
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			}
			setError(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

function setError(data){
	//console.log(data);
	var inputArray = $("#errorFrm").find("input");
	
	inputArray[2].value = data.error02_1.condition1/100.0;
	inputArray[3].value = data.error02_1.condition3/100.0;
	inputArray[4].value = data.error02_1.sec/100.0;
	inputArray[5].value = data.error02_1.tc3/100.0;
	
	inputArray[6].value = data.error02_2.condition1/100.0;
	inputArray[7].value = data.error02_2.condition3/100.0;
	inputArray[8].value = data.error02_2.sec/100.0;
	inputArray[9].value = data.error02_2.tc3/100.0;
	
	inputArray[10].value = data.error02_3.condition1/100.0;
	inputArray[11].value = data.error02_3.condition3/100.0;
	inputArray[12].value = data.error02_3.sec/100.0;
	inputArray[13].value = data.error02_3.tc3/100.0;
	
	inputArray[14].value = data.error15_1.condition1/100.0;
	inputArray[15].value = data.error15_1.condition3/100.0;
	inputArray[16].value = data.error15_1.sec/100.0;
	inputArray[17].value = data.error15_1.tc3/100.0;

	inputArray[18].value = data.error08_1.condition1/100.0;
	inputArray[19].value = data.error08_1.condition3/100.0;
	inputArray[20].value = data.error08_1.sec/100.0;
	inputArray[21].value = data.error08_1.tc3/100.0;

	inputArray[22].value = data.error08_2.condition1/100.0;
	inputArray[23].value = data.error08_2.condition3/100.0;
	inputArray[24].value = data.error08_2.sec/100.0;
	inputArray[25].value = data.error08_2.tc3/100.0;
	
}

// 운전공정 수정
function insertControll(rtuId,iBdNum){
	
	var editValue = $("#controll").find("tbody").find("input[class='textback form-control']"); 
	var editValueArray = [];
	for(var i=0; i < editValue.length; i++ ) {
		editValueArray.push($(editValue[i]).val())
	}

	$.ajax({
		 url :'/systemCont/insertControll'
		,method : 'POST'
		,traditional: true
		,dataType : 'JSON'
		,data : {
					'editValue': editValueArray,
					rtuId:rtuId,
					iBdNum:iBdNum
				}
		,success : function(data) {
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
				// 성공했을 경우 정보 세팅
				setControll(data.res);
			}
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	});
}

// 운전공정 데이터 조회
function getControll(rtuId,iBdNum){
	
	$.ajax({
		url : '/systemCont/getControll'
		,data : {
			rtuId:rtuId,
			iBdNum:iBdNum
		}
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			} else {
				// 성공시에 정보 세팅
				setControll(data.res);	
			}
			
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

// 운전공정 데이터 세팅
function setControll(data){
	// console.log(data);
	var inputArray = $("#controll").find("tbody").find("input[class='textback form-control']"); 
	
	inputArray[0].value = data.pump1_1.condition3/100.0;
	inputArray[1].value = data.pump1_1.speedValue/100.0;
	inputArray[2].value = data.pump1_1.targetValue/100.0;	
	
	inputArray[3].value = data.pump1_2.condition1/100.0;
	inputArray[4].value = data.pump1_2.condition3/100.0;
	inputArray[5].value = data.pump1_2.speedValue/100.0;
	inputArray[6].value = data.pump1_2.targetValue/100.0;
	
	inputArray[7].value = data.pump1_3.condition1/100.0;
	inputArray[8].value = data.pump1_3.condition3/100.0;
	inputArray[9].value = data.pump1_3.speedValue/100.0;
	inputArray[10].value = data.pump1_3.targetValue/100.0;
	
	inputArray[11].value = data.pump1_4.condition1/100.0;
	inputArray[12].value = data.pump1_4.condition3/100.0;
	inputArray[13].value = data.pump1_4.speedValue/100.0;
	inputArray[14].value = data.pump1_4.targetValue/100.0;
	
	inputArray[15].value = data.pump1_5.condition1/100.0;
	inputArray[16].value = data.pump1_5.condition3/100.0;
	inputArray[17].value = data.pump1_5.speedValue/100.0;
	inputArray[18].value = data.pump1_5.targetValue/100.0;
	
	inputArray[19].value = data.pump1_6.condition1/100.0;
	inputArray[20].value = data.pump1_6.condition3/100.0;
	inputArray[21].value = data.pump1_6.speedValue/100.0;
	inputArray[22].value = data.pump1_6.targetValue/100.0;
	
	inputArray[23].value = data.pump1_7.condition1/100.0;
	inputArray[24].value = data.pump1_7.condition3/100.0;
	inputArray[25].value = data.pump1_7.speedValue/100.0;
	inputArray[26].value = data.pump1_7.targetValue/100.0;
	
	inputArray[27].value = data.pump1_8.condition1/100.0;
	inputArray[28].value = data.pump1_8.condition3/100.0;
	inputArray[29].value = data.pump1_8.speedValue/100.0;
	inputArray[30].value = data.pump1_8.targetValue/100.0;
	
	inputArray[31].value = data.pump1_9.condition1/100.0;
	inputArray[32].value = data.pump1_9.condition3/100.0;
	inputArray[33].value = data.pump1_9.speedValue/100.0;
	inputArray[34].value = data.pump1_9.targetValue/100.0;
	
	inputArray[35].value = data.pump1_10.condition1/100.0;
	inputArray[36].value = data.pump1_10.condition3/100.0;
	inputArray[37].value = data.pump1_10.speedValue/100.0;
	inputArray[38].value = data.pump1_10.targetValue/100.0;
	
	inputArray[39].value = data.pump1_11.condition1/100.0;
	inputArray[40].value = data.pump1_11.condition3/100.0;
	inputArray[41].value = data.pump1_11.speedValue/100.0;
	inputArray[42].value = data.pump1_11.targetValue/100.0;
	
	inputArray[43].value = data.pump1_12.condition1/100.0;
	inputArray[44].value = data.pump1_12.condition3/100.0;
	inputArray[45].value = data.pump1_12.speedValue/100.0;
	inputArray[46].value = data.pump1_12.targetValue/100.0;

	inputArray[47].value = data.pump1_13.condition1/100.0;
	inputArray[48].value = data.pump1_13.speedValue/100.0;
	inputArray[49].value = data.pump1_13.targetValue/100.0;

	inputArray[50].value = data.pump1_14.condition1/100.0;
	inputArray[51].value = data.pump1_14.speedValue/100.0;
	inputArray[52].value = data.pump1_14.targetValue/100.0;

	inputArray[53].value = data.pump1_15.condition1/100.0;
	inputArray[54].value = data.pump1_15.speedValue/100.0;
	inputArray[55].value = data.pump1_15.targetValue/100.0;

	inputArray[56].value = data.pump3_1.condition3/100.0;
	inputArray[57].value = data.pump3_1.speedValue/100.0;
	inputArray[58].value = data.pump3_1.targetValue/100.0;
	
	inputArray[59].value = data.pump3_2.condition1/100.0;
	inputArray[60].value = data.pump3_2.condition3/100.0;
	inputArray[61].value = data.pump3_2.speedValue/100.0;
	inputArray[62].value = data.pump3_2.targetValue/100.0;
	
	inputArray[63].value = data.pump3_3.condition1/100.0;
	inputArray[64].value = data.pump3_3.condition3/100.0;
	inputArray[65].value = data.pump3_3.speedValue/100.0;
	inputArray[66].value = data.pump3_3.targetValue/100.0;
	
	inputArray[67].value = data.pump3_4.condition1/100.0;
	inputArray[68].value = data.pump3_4.condition3/100.0;
	inputArray[69].value = data.pump3_4.speedValue/100.0;
	inputArray[70].value = data.pump3_4.targetValue/100.0;
	
	inputArray[71].value = data.pump3_5.condition1/100.0;
	inputArray[72].value = data.pump3_5.condition3/100.0;
	inputArray[73].value = data.pump3_5.speedValue/100.0;
	inputArray[74].value = data.pump3_5.targetValue/100.0;

	inputArray[75].value = data.pump3_6.condition1/100.0;
	inputArray[76].value = data.pump3_6.speedValue/100.0;
	inputArray[77].value = data.pump3_6.targetValue/100.0;

	inputArray[78].value = data.pump4_1.condition3/100.0;
	inputArray[79].value = data.pump4_1.speedValue/100.0;
	inputArray[80].value = data.pump4_1.targetValue/100.0;
	
	inputArray[81].value = data.pump4_2.condition1/100.0;
	inputArray[82].value = data.pump4_2.condition3/100.0;
	inputArray[83].value = data.pump4_2.speedValue/100.0;
	inputArray[84].value = data.pump4_2.targetValue/100.0;

	inputArray[85].value = data.pump4_3.condition1/100.0;
	inputArray[86].value = data.pump4_3.condition3/100.0;
	inputArray[87].value = data.pump4_3.speedValue/100.0;
	inputArray[88].value = data.pump4_3.targetValue/100.0;

	inputArray[89].value = data.pump4_4.condition1/100.0;
	inputArray[90].value = data.pump4_4.speedValue/100.0;
	inputArray[91].value = data.pump4_4.targetValue/100.0;

	inputArray[92].value = data.pcs1_1.condition1/100.0;
	inputArray[93].value = data.pcs1_1.speedValue/100.0;

	inputArray[94].value = data.pcs1_2.condition3/100.0;
	inputArray[95].value = data.pcs1_2.speedValue/100.0;
	inputArray[96].value = data.pcs1_2.targetValue/100.0;
	
	inputArray[97].value = data.pcs1_3.condition3/100.0;
	inputArray[98].value = data.pcs1_3.speedValue/100.0;
	inputArray[99].value = data.pcs1_3.targetValue/100.0;

	inputArray[100].value = data.pump2_1.condition3/100.0;
	inputArray[101].value = data.pump2_1.targetValue/100.0;

	inputArray[102].value = data.pump2_2.condition3/100.0;
	
	inputArray[103].value = data.pump2_3.targetValue/100.0;
	
	inputArray[104].value = data.pump2_4.condition3/100.0;
}


/****************************************
 * 설명: 파라미터 변경 팝업의 온오프 버튼 이벤트
 * 파라미터
 *	- data: 자기자신(this)
 ****************************************/
function fncOnOffBtn(data) {
	if(data.value == "ON") {
		data.value = "OFF";
		$(data).parent().find(".btnValue").val("0");
	} else {
		data.value = "ON";
		$(data).parent().find(".btnValue").val("1");
	}
	
}

/****************************************
 * 설명: 파라메타 수정
 ****************************************/
function insertParameters(comeVal,rtuId,iBdNum){
	var param = {};
	var parametersInput = $("#parametersForm").find("input");
	
	// 파라메타 수정값 세팅
	for(var i=0; i < parametersInput.length; i++ ) {
		var arrName = $(parametersInput[i]).attr("name");
		if(arrName != undefined) {
			if(arrName.indexOf("Select") == -1) {
				if($(parametersInput[i]).val() == "") {
					param[arrName] = "0";	
				} else {
					param[arrName] = $(parametersInput[i]).val();
				}
			} else {
				if($('input:checkbox[name="'+arrName+'"]').is(":checked") == true) {
					param[arrName] = "1";	
				} else {
					param[arrName] = "0";
				}
			}	
		}
	}
	
	param['u8'] = comeVal;
	param['rtuId'] = rtuId;
	param['iBdNum'] = iBdNum;
	
	$.ajax({
		 url :'/systemCont/insertParameter'
		,method : 'POST'
		,dataType : 'JSON'
		,contentType: "application/json;"
		,data : JSON.stringify(param)
		,success : function(data) {
			if(data.res == null){
				alert("데이터 전송에 실패했습니다.");
				// TODO modal close
			}else{
				alert("정상처리 되었습니다.");
				// 성공시에 정보 세팅
				setParameter(data.res);
			}
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	});
}

/****************************************
 * 설명: 파라메타 데이터 세팅
 * 파라미터
 *	- data: 세팅 데이터 정보
 ****************************************/
function setParameter(data){
	 //console.log(data);

	// parametersForm에 있는 전체 input 데이터 
	var inputArray = $("#parametersForm").find("input");
	
	for(var i=0; i < inputArray.length; i++) {
		var arrName = $(inputArray[i]).attr("name");
		
		// ON/OFF 버튼의 경우 name 존재 하지 않기 떄문에 undefined 체크
		if(arrName != undefined) {

			var selectData = $(inputArray[i]).attr("name").split("Select");
			var valueData  = $(inputArray[i]).attr("name").split("Value");

			// checkBox 값 세팅
			
			if(selectData.length == 2) {
				var selectKey = selectData[0];
				if(data[selectKey].select/100.0 == 1) {
					$(inputArray[i]).prop('checked', true);
				} else {
					$(inputArray[i]).prop('checked', false);
				}
			}
			
			// value 값 세팅 
			var valueKey = valueData[0];
			if(valueData.length == 2) {
				var valueVal = data[valueKey].value/100.0;
				inputArray[i].value = valueVal;
				
				// ON/OFF 적용
				if(valueVal == 0) {
					$(inputArray[i]).parent().find("input[type='button']").val("OFF");	
				} else {
					$(inputArray[i]).parent().find("input[type='button']").val("ON");
				}
				
			}
		}
	}
}

//발전 시작 or 종료
function insertGeneratorMode(mode,rtuId,iBdNum){
	
	var params = {};
	
	if(mode == 1){
		params = {
			"start" : 1,
			"stop" :  0,
			"rtuId" : rtuId,
			"iBdNum" : iBdNum
			
		}
	}else{
		params = {
			"start" : 0,
			"stop" :  1,
			"rtuId" : rtuId,
			"iBdNum" : iBdNum
		}
	}

	$.ajax({
		url : '/systemCont/insertGeneratorMode'
		,data : params
		,dataType : 'json'
		,type : 'post'				
		,success : function(data){
			if(data.res == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			}else{
				alert("요청 성공");
			}
			//setError(data.res);
		}
	    ,beforeSend : function(xhr) {
	        xhr.setRequestHeader("AJAX", true);
	        //-TODO : LOADING IMG
	    }
	    ,error : function(xhr,status,error) {
	        //-TODO : LOADING IMG 제거
	        /* if(xhr.status == 401) {
	            alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else if(xhr.status == 403) {
	            alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	        } else {
	        	alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
	            location.href = "/";
	            //alert("["+xhr.status+"]오류입니다.\n");
	    		return;	
	        } */
	    }
	})
}

// 에러종료
function insertErrorStopMode(rtuId,iBdNum){

	var params = {
		"stop" :  1,
		"rtuId" : rtuId,
		"iBdNum" : iBdNum
	}

	$.ajax({
		url : '/systemCont/insertErrorStopMode'
		,data : params
		,dataType : 'json'
		,type : 'post'
		,success : function(data){
			if(data.res == null){
				alert("조회에 실패했습니다.");
				// TODO modal close
			}else{
				alert("요청 성공");
			}
			//setError(data.res);
		}
		,beforeSend : function(xhr) {
			xhr.setRequestHeader("AJAX", true);
			//-TODO : LOADING IMG
		}
		,error : function(xhr,status,error) {
			//-TODO : LOADING IMG 제거
			/* if(xhr.status == 401) {
                alert("인증에 실패 했습니다. 메인 페이지로 이동합니다.");
                location.href = "/";
            } else if(xhr.status == 403) {
                alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
                location.href = "/";
            } else {
                alert("세션이 만료가 되었습니다. 메인 페이지로 이동합니다.");
                location.href = "/";
                //alert("["+xhr.status+"]오류입니다.\n");
                return;
            } */
		}
	})
}

$(document).ready(function() {
	$(".gain, .offset, .tc").on("change", function(){
	    if($(this).is(":checked")){
	        $(this).parent().find("input[type='hidden']").val("1");
	    }else{
	        $(this).parent().find("input[type='hidden']").val("0");
	    }
	});
});
</script>
