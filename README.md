react-native-simplemenu-android
===============================

Basic react-native package for showing an menu based on
[AlertDialog](https://developer.android.com/reference/android/app/AlertDialog.Builder.html).

This can be used as a replacement for `ActionSheetIOS.showActionSheetWithOptions`.

**Important note:** this is not the recommended way anymore to show a popup-menu on Android.
[Use UIManager.showPopupMenu](https://github.com/facebook/react-native/issues/3004#issuecomment-202598006) instead,
and hope that an official API will appear someday.


Install
-------

1. `npm install --save react-native-simplemenu-android`

2. Add to `android/settings.gradle`
```gradle
...
include ':react-native-simplemenu-android'
project(':react-native-simplemenu-android').projectDir = new File(settingsDir, '../node_modules/react-native-simplemenu-android/android')
```

3. Add to `android/app/build.gradle`
```gradle
...
dependencies {
    ...
    compile project(':react-native-simplemenu-android')
}
```
4. Register module in `MainActivity.java`
```java
import org.thequestionmark.rnsimplemenu.SimpleMenuPackage;

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ...

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new SimpleMenuPackage(this)) // <------ add this line to yout MainActivity class, don't forget the `this` argument in constructor
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "AndroidRNSample", null);

    setContentView(mReactRootView);
  }

  ...

}
```

Use
---

```javascript
import Menu from 'react-native-simplemenu-android';

function onDoSomething() {
  Menu.show({
    options: ['Show it to me', 'Log me']
  }, function(i) {
    if (i == 0) {
      onShowIt();
    } else if (i == 1) {
      console.log('Logme pressed');
    }
  });
}
```

Credits
-------

Thanks to [marcshilling/react-native-image-picker](https://github.com/marcshilling/react-native-image-picker) for something to start with.
