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
		
	
	_transsion.connect("on_init_completed",self,"on_init_completed")
	_transsion.connect("on_init_failed",self,"on_init_failed")
	_transsion.connect("on_plugin_error",self,"on_plugin_error")
	_transsion.connect("on_appopen_loaded",self,"on_appopen_loaded")
	_transsion.connect("on_appopen_showed",self,"on_appopen_showed")
	_transsion.connect("on_appopen_closed",self,"on_appopen_closed")
	_transsion.connect("on_appopen_clicked",self,"on_appopen_clicked")
	_transsion.connect("on_rewarded_showed",self,"on_rewarded_showed")
	_transsion.connect("on_rewarded_closed",self,"on_rewarded_closed")
	_transsion.connect("on_rewarded_clicked",self,"on_rewarded_clicked")
	_transsion.connect("on_rewarded_loaded",self,"on_rewarded_loaded")
	_transsion.connect("on_rewarded",self,"on_rewarded")
	_transsion.connect("on_interstitial_loaded",self,"on_interstitial_loaded")
	_transsion.connect("on_interstitial_showed",self,"on_interstitial_showed")
	_transsion.connect("on_interstitial_closed",self,"on_interstitial_closed")
	_transsion.connect("on_interstitial_clicked",self,"on_interstitial_clicked")
	_transsion.connect("on_banner_loaded",self,"on_banner_loaded")
	_transsion.connect("on_banner_showed",self,"on_banner_showed")
	_transsion.connect("on_banner_closed",self,"on_banner_closed")

# =================================== LISTENER ===========================
func on_init_completed():
	emit_signal("on_init_completed")

func on_init_failed(message):
	emit_signal("on_init_failed",message)

func on_plugin_error(message):
	emit_signal("on_plugin_error",message)

func on_appopen_loaded():
	emit_signal("on_appopen_loaded")

func on_appopen_showed():
	emit_signal("on_appopen_showed")

func on_appopen_closed():
	emit_signal("on_appopen_closed")

func on_appopen_clicked():
	emit_signal("on_appopen_clicked")

func on_rewarded_showed():
	emit_signal("on_rewarded_showed")

func on_rewarded_closed():
	emit_signal("on_rewarded_closed")

func on_rewarded_clicked():
	emit_signal("on_rewarded_clicked")

func on_rewarded_loaded():
	emit_signal("on_rewarded_loaded")

func on_interstitial_loaded():
	emit_signal("on_interstitial_loaded")

func on_interstitial_showed():
	emit_signal("on_interstitial_showed")

func on_interstitial_closed():
	emit_signal("on_interstitial_closed")

func on_interstitial_clicked():
	emit_signal("on_interstitial_clicked")

func on_banner_loaded():
	emit_signal("on_banner_loaded")

func on_banner_showed():
	emit_signal("on_banner_showed")

func on_banner_closed():
	emit_signal("on_banner_closed")


func init() -> void:
	if _transsion != null:
		_transsion.init()


func isInitialized()->bool:
	if _transsion != null:
		if _transsion.isInitialized():
			print("Transsion SDK inited")
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


