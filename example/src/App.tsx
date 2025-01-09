import {
  AlertDialog,
  DatePicker,
  Menu,
  OptionsDialog,
  Snackbar,
  TimePicker,
  Colors,
  RangePicker,
} from '@material3/react-native';
import { forwardRef } from 'react';
import {
  StyleSheet,
  ScrollView,
  type PressableProps,
  Pressable,
  Text,
} from 'react-native';

export default function App() {
  return (
    <ScrollView
      style={styles.container}
      contentContainerStyle={styles.scrollPadding}
    >
      <DatePickerButton />
      <TimePickerButton />
      <RangePickerButton />
      <SnackbarButton />
      <MenuButton />
      <AlertDialogButton />
      <OptionsDialogButton />
      <SingleChoiceOptionsDialogButton />
      <MultipleChoiceOptionsDialogButton />
      <PrintLightColorsButton />
      <PrintDarkColorsButton />
    </ScrollView>
  );
}

function DatePickerButton() {
  function show() {
    DatePicker.show({
      fullscreen: false,
      inputMode: 'calendar',
      maxDate: undefined,
      minDate: undefined,
      value: undefined,
      firstDayOfWeek: 0,
      title: 'CHOOSE A DATE',
      negativeButtonText: 'CANCEL',
      positiveButtonText: 'OK',
      onChange: console.log,
    });
  }

  return <Button onPress={show}>Date Picker</Button>;
}

function TimePickerButton() {
  function show() {
    TimePicker.show({
      inputMode: 'clock',
      title: 'CHOOSE A TIME',
      negativeButtonText: 'CANCEL',
      positiveButtonText: 'OK',
      onChange: console.log,
      hour: 2,
      minute: 45,
      is24HourFormat: false,
    });
  }

  return <Button onPress={show}>Time Picker</Button>;
}

function RangePickerButton() {
  function show() {
    RangePicker.show({
      title: 'CHOOSE A RANGE',
      negativeButtonText: 'CANCEL',
      positiveButtonText: 'OK',
      onChange: console.log,
      maxDate: undefined,
      minDate: undefined,
      value: { start: undefined, end: undefined },
      firstDayOfWeek: 0,
      fullscreen: true,
      inputMode: 'calendar',
    });
  }
  return <Button onPress={show}>Range Picker</Button>;
}

function SnackbarButton() {
  function show() {
    Snackbar.show({
      text: 'Saved photos!',
      action: {
        text: 'UNDO',
        onPress: () => console.log('Undo'),
      },
      animationMode: 'fade',
      duration: 'short',
      textMaxLines: 1,
    });
  }

  return <Button onPress={show}>Snackbar</Button>;
}

function MenuButton() {
  return (
    <Menu>
      <Menu.Trigger>
        <Button>Menu</Button>
      </Menu.Trigger>
      <Menu.Items>
        <Menu.Item
          onSelect={() => console.log('Item 1 pressed')}
          title="Bold"
          groupId={1}
          icon="baseline_access_alarm_24"
          isCheckable={true}
        />
        <Menu.Item
          onSelect={() => console.log('Item 2 pressed')}
          title="Italic"
          groupId={1}
          icon="baseline_access_alarm_24"
          isCheckable={true}
          isChecked={true}
        />
        <Menu.Item
          onSelect={() => console.log('Item 3 pressed')}
          title="Delete"
          groupId={2}
        />

        <Menu.SubMenu
          title="More options"
          groupId={3}
          icon="baseline_access_alarm_24"
        >
          <Menu.Item
            onSelect={() => console.log('Item 4 pressed')}
            title="Restore default"
          />
        </Menu.SubMenu>
      </Menu.Items>
    </Menu>
  );
}

function AlertDialogButton() {
  function show() {
    AlertDialog.show({
      title: 'Discard draft?',
      message: 'Your changes will be lost.',
      negativeButtonText: 'CANCEL',
      positiveButtonText: 'DISCARD',
      onCancel: () => console.log('Cancel'),
      onPositivePress: () => console.log('Confirmed'),
      onNegativePress: () => console.log('Negative pressed'),
      cancelable: true,
      headerAlignment: 'start',
      icon: 'baseline_access_alarm_24',
      neutralButtonText: 'NEUTRAL',
      onNeutralPress: () => console.log('Neutral pressed'),
    });
  }

  return <Button onPress={show}>Alert Dialog</Button>;
}

function OptionsDialogButton() {
  function show() {
    OptionsDialog.show({
      title: 'Next steps',
      options: ['Go home', 'Go to work', 'Go to school'],
      cancelable: true,
      headerAlignment: 'start',
      onCancel: () => console.log('Cancel'),
      negativeButtonText: 'CANCEL',
      onNegativePress: () => console.log('Negative pressed'),
      neutralButtonText: 'NEUTRAL',
      onNeutralPress: () => console.log('Neutral pressed'),
      onPositivePress: (selectedIndex) =>
        console.log('Option selected:', selectedIndex),
      pickerType: 'row',
      positiveButtonText: 'OK',
    });
  }
  return <Button onPress={show}>Options Dialog</Button>;
}

function SingleChoiceOptionsDialogButton() {
  function show() {
    OptionsDialog.show({
      title: 'Choose a color',
      options: ['Red', 'Green', 'Blue'],
      cancelable: true,
      headerAlignment: 'start',
      onCancel: () => console.log('Cancel'),
      negativeButtonText: 'CANCEL',
      onNegativePress: () => console.log('Negative pressed'),
      neutralButtonText: 'NEUTRAL',
      onNeutralPress: () => console.log('Neutral pressed'),
      onPositivePress: (selectedIndex) =>
        console.log('Option selected:', selectedIndex),
      pickerType: 'singlechoice',
      positiveButtonText: 'OK',
      icon: 'baseline_access_alarm_24',
    });
  }
  return <Button onPress={show}>Single Choice Options Dialog</Button>;
}

function MultipleChoiceOptionsDialogButton() {
  function show() {
    OptionsDialog.show({
      title: 'Choose your activities',
      options: ['Biking', 'Hiking', 'Swimming', 'Running'],
      cancelable: true,
      headerAlignment: 'start',
      onCancel: () => console.log('Cancel'),
      negativeButtonText: 'CANCEL',
      onNegativePress: () => console.log('Negative pressed'),
      neutralButtonText: 'NEUTRAL',
      onNeutralPress: () => console.log('Neutral pressed'),
      onPositivePress: (selectedIndexes) =>
        console.log('Options selected:', selectedIndexes),
      pickerType: 'multiselect',
      positiveButtonText: 'OK',
      icon: 'baseline_access_alarm_24',
      selected: [0, 1],
    });
  }
  return <Button onPress={show}>Multiple Choice Options Dialog</Button>;
}

function PrintLightColorsButton() {
  function show() {
    console.log(JSON.stringify(Colors.getColors().light, null, 2));
  }
  return <Button onPress={show}>Print Light Colors</Button>;
}

function PrintDarkColorsButton() {
  function show() {
    console.log(JSON.stringify(Colors.getColors().dark, null, 2));
  }
  return <Button onPress={show}>Print Dark Colors</Button>;
}

const Button = forwardRef(function Button(
  { children, ...props }: PressableProps & { children: string },
  ref
) {
  const primaryColor = Colors.getColors().light.primaryContainer;
  const rippleColor = `${primaryColor}33`;
  return (
    <Pressable
      ref={ref}
      {...props}
      style={styles.button}
      android_ripple={{ color: rippleColor }}
    >
      <Text>{children}</Text>
    </Pressable>
  );
});

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  button: {
    height: 55,
    paddingHorizontal: 15,
    justifyContent: 'center',
  },
  scrollPadding: {
    paddingVertical: 15,
  },
});
