import NativeDatePicker, { type ShowProps } from '../specs/NativeDatePicker';

function show({
  onChange,
  onCancel,
  value,
  maxDate,
  minDate,
  ...props
}: DatePickerProps): Promise<void> {
  function handleChange(newTimestamp: number) {
    onChange(new Date(newTimestamp));
  }

  return NativeDatePicker.show(
    {
      value: value?.getTime(),
      maxDate: maxDate?.getTime(),
      minDate: minDate?.getTime(),
      ...props,
    },
    handleChange,
    onCancel
  );
}

export const DatePicker = {
  show,
};

export type DatePickerProps = Omit<
  ShowProps,
  'value' | 'inputMode' | 'maxDate' | 'minDate'
> & {
  onChange: (newValue: Date) => void;
  onCancel?: () => void;
  value?: Date;
  maxDate?: Date;
  minDate?: Date;
  inputMode?: 'text' | 'calendar';
};
