extends Node

var _transsion = null
var TOP = "TOP"
var BOTTOM = "BOTTOM"

signal on_banner_loaded
signal on_banner_showed
signal on_banner_closed

signal on_interstitial_loaded
signal on_interstitial_showed
signal on_interstitial_closed
signal on_interstitial_clicked

signal on_rewarded_showed
signal on_rewarded_closed
signal on_rewarded_clicked
signal on_rewarded_loaded
signal on_rewarded

signal on_appopen_loaded
signal on_appopen_showed
signal on_appopen_closed
signal on_appopen_clicked

signal on_plugin_error
signal on_init_completed
signal on_init_failed

func _ready():
	if Engine.has_singleton("GodotTranssion"):
		_transsion = Engine.get_singleton("GodotTranssion")
	else:
		push_warning('GodotTranssion plugin not found!')
		
	
	_transsion.connect("on_init_completed",self,"_on_start_connection_success")
	_transsion.connect("on_init_failed",self,"_on_query_purchases_response")
	_transsion.connect("on_plugin_error",self,"_on_handle_purchase")
	_transsion.connect("on_appopen_loaded",self,"_on_acknowledge_success")
	_transsion.connect("on_appopen_showed",self,"_on_consume_success")
	_transsion.connect("on_appopen_closed",self,"_on_product_details_response")
	_transsion.connect("on_appopen_clicked",self,"_on_product_details_response")
	_transsion.connect("on_rewarded_showed",self,"_on_product_details_response")
	_transsion.connect("on_rewarded_closed",self,"_on_product_details_response")
	_transsion.connect("on_rewarded_clicked",self,"_on_product_details_response")
	_transsion.connect("on_rewarded_loaded",self,"_on_product_details_response")
	_transsion.connect("on_rewarded",self,"_on_product_details_response")
	_transsion.connect("on_interstitial_loaded",self,"_on_product_details_response")
	_transsion.connect("on_interstitial_showed",self,"_on_product_details_response")
	_transsion.connect("on_interstitial_closed",self,"_on_product_details_response")
	_transsion.connect("on_interstitial_clicked",self,"_on_product_details_response")
	_transsion.connect("on_banner_loaded",self,"_on_product_details_response")
	_transsion.connect("on_banner_showed",self,"_on_product_details_response")
	_transsion.connect("on_banner_closed",self,"_on_product_details_response")
	

	

func init(licenseKey: String) -> void:
	if _transsion != null:
		_transsion.init(licenseKey)
		print('Transsion plugin inited!')
		
func isInitialized()->bool:
	if _transsion != null:
		return _transsion.isInitialized();
	return false

func initBanner()->void:
	if _transsion!=null:
		_transsion.initBanner();

func hideBanner()->void:
	if _transsion != null:
		_transsion.hideBanner()

func destroyBanner()->void:
	if _transsion != null:
		_transsion.destroyBanner()

func setBannerLocation(location=TOP)->void:
	if _transsion != null:
		_transsion.setBannerLocation(location)
		
func loadInterstitial()->void:
	if _transsion != null:
		_transsion.loadInterstitial()

func showInterstitial()->void:
	if _transsion != null:
		_transsion.showInterstitial()

func destroyInterstitial()->void:
	if _transsion != null:
		_transsion.destroyInterstitial()

func loadRewarded()->void:
	if _transsion != null:
		_transsion.loadRewarded()
		
func showRewarded()->void:
	if _transsion != null:
		_transsion.showRewarded()

func destroyRewarded()->void:
	if _transsion != null:
		_transsion.destroyRewarded()

func loadAppOpen()->void:
	if _transsion != null:
		_transsion.loadAppOpen()
		
func showAppOpen()->void:
	if _transsion != null:
		_transsion.showAppOpen()

func destroyAppOpen()->void:
	if _transsion != null:
		_transsion.showAppOpen()

# =================================== LISTENER ===========================
func _on_start_connection_success():
	emit_signal("on_start_connection_success")

func _on_query_purchases_response(purchaseIDs:Array):
	emit_signal("on_query_purchases_response",purchaseIDs)
	
func _on_handle_purchase(productId:String):
	emit_signal("on_handle_purchase",productId)
	
func _on_acknowledge_success(purchaseId:String):
	emit_signal("on_acknowledge_success",purchaseId)
	
func _on_consume_success(purchaseId:String):
	emit_signal("on_consume_success",purchaseId)

func _on_product_details_response(productDetails:Array):
	emit_signal("on_product_details_response",productDetails)
