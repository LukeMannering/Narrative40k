## Contents
1. Getting started
    1. Components that need configuring
2. Activities and Fragments
    1. Adding a new Activity
    2. Adding a new Fragment
    
# 1 - Getting started
## 1.1 - Components that need configuring
These components require configuration that is unique to each project/app. Go to the relevant site for installation instructions

### Fabric
Api key in manifest has been replaced with a placeholder. The `apply plugin` line in the Gradle file has been commented out along with the Maven repository block below it.

### Firebase
The google-services.json file in the project has been emptied and the `apply plugin` line in the Gradle file has been comments out.

# 2 - Getting started
## 2.1 - Adding a new Activity

The Activity must implement `HasSupportFragmentInjector` by declaring this method with the corresponding member field:
```
@Inject
DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;

...

@Override
public AndroidInjector<Fragment> supportFragmentInjector() {
    return mDispatchingAndroidInjector;
}
```

The Activity needs adding to the Dagger2 Activities module `DependencyInjection.Modules.ActivitiesModule` by adding two lines like this. The method name is arbitrary but we just stick with the convention.

```
@ContributesAndroidInjector(modules = FragmentBuildersModule.class)
abstract AuthorisationActivity contributeAuthorisationActivity();
```

## 2.2 - Adding a new Fragment

### Create the fragment's ViewModel

Create a ViewModel class in the ViewModels directory. Use constructor injection to bring in dependencies. Then assign the ones you need to member fields e.g.
```
@SuppressWarnings("unchecked")
@Inject
public NoticeDetailsViewModel(Application app,
                              NoticesRepository noticesRepository,
                              PupilsRepository pupilsRepository) {
                              
    mNoticesRepository = noticesRepository;
    mPupilsRepository = pupilsRepository;
```

Add a Dagger2 binding to map the ViewModel's class name to the actual class. In the `DependencyInjection.Modules.ViewModelModule` add the following, the name of the method doesn't matter but there's just a convetion:

```
@Binds
@IntoMap
@ViewModelKey(NoticeDetailsViewModel.class)
abstract ViewModel bindNoticeDetailsModel(NoticeDetailsViewModel viewModel);
```

### Create the fragment

Needs to `implement Injectable` which is a dummy interface used to tell Dagger2 which fragments to register. 

The fragment needs to have the ViewModelFactory injected via field injection
```
@Inject
ViewModelProvider.Factory mViewModelFactory;
```

Store the ViewModel itself in a member field too:
```
private NoticeDetailsViewModel mViewModel;
```

Call the view model getter code from the `onActivityCreated` method, not on `onCreateView` as it won't have been created yet by Dagger2

```
@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NoticeDetailsViewModel.class);
    ...

```

Add a contributer for the fragment to the Dagger2 fragment module `DependencyInjection.Modules.FragmentBuildersModule`. This is just two lines e.g. 

```
@ContributesAndroidInjector
abstract NoticeDetailsFragment contributeNoticeDetailsFragment();
```
