
# react-native-launch-intent-package

## Getting started

`$ npm install react-native-launch-intent-package --save`

change version to `'https://github.com/CantFayle/react-native-launch-intent-package'`

### Mostly automatic installation

`$ react-native link react-native-launch-intent-package`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.PackageIntentPackage;` to the imports at the top of the file
  - Add `new PackageIntentPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-launch-intent-package'
  	project(':react-native-launch-intent-package').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-launch-intent-package/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-launch-intent-package')
  	```

## Usage
```javascript
import { PackageIntentAndroid } from 'react-native-launch-intent-package';

//get all available apps
const appList = PackageIntentAndroid.getAppList();

const packageName = 'com.android.settings';
let status = false; //bool
PackageIntentAndroid.canStartIntent(packageName, canStartIntent => {
  if (canStartIntent) {
    status = PackageIntentAndroid.startIntent(packageName);
  }
});
console.log(status ? 'success!' : 'failure!');
```
