import 'package:flutter/services.dart';

class WitsBlue {
  static const platform = const MethodChannel('witsystem.top/blue');
  ///开启感应开锁
  Future<bool> openInduceUnlock() async {
    try {
      final bool result = await platform.invokeMethod('openInduceUnlock');
      return result;
    } on PlatformException catch (e) {
      print('调用开启失败');
      return false;
    }
  }
}
