Reamaze SDK for Android
===================

[Reamaze](https://www.reamaze.com/) allows you to embed a support knowledge base into your app.


Sample app
----------

For a quick first look at Reamaze, we have included a very small sample application that you can build and run. The sample app demonstrates invoking a ReamazeActivity.


Installation
------------

1. Clone this repository
1. Be sure you've installed the Android SDK with API Level 19
1. Import the "reamaze" folder into Eclipse (use "Existing Projects into Workspace", not "Existing Android Code").
1. In your project settings, add the Reamaze project under the "Libraries" section of the "Android" category.
1. In your AndroidManifest, make sure you are requiring permissions for INTERNET and declare the ReamazeActivity.
1. In your .java file, you need to import the Reamaze SDK.

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

```xml
<activity android:name="com.reamaze.sdk.ReamazeActivity" />
```

```java
import com.reamaze.sdk.*;
```

Instructions
------------

#### Integrate


Trigger Reamaze from anywhere (e.g. Button Press)

```java
// SomeActivity.java
public void onHelpClicked(View view) {
  Intent intent = new Intent(this, ReamazeActivity.class);
  intent.putExtra("brand", "YOUR BRAND HERE"); // Get your brand from the subdomain of your public site. (e.g. foobar.reamaze.com is brand "foobar")
  startActivity(intent);
}
```

That's all you need to do!
