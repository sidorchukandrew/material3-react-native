import NativeTimePicker, { type ShowProps } from '../specs/NativeTimePicker';

function show({
  onChange,
  onCancel,
  ...props
}: TimePickerProps): Promise<void> {
  return NativeTimePicker.show(
    {
      ...props,
    },
    onChange,
    onCancel
  );
}

export const TimePicker = {
  show,
};

export type TimePickerProps = Omit<ShowProps, 'value' | 'inputMode'> & {
  onChange: (result: { hour: number; minute: number }) => void;
  onCancel?: () => void;
  inputMode?: 'keyboard' | 'clock';
};
