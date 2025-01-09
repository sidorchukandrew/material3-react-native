import NativeRangePicker, { type ShowProps } from '../specs/NativeRangePicker';

export type Range = {
  start: Date;
  end: Date;
};

function show({
  onChange,
  onCancel,
  value,
  maxDate,
  minDate,
  ...props
}: RangePickerProps): Promise<void> {
  function handleChange(startTimestamp: number, endTimestamp: number) {
    onChange({ start: new Date(startTimestamp), end: new Date(endTimestamp) });
  }

  return NativeRangePicker.show(
    {
      start: value?.start?.getTime(),
      end: value?.end?.getTime(),
      maxDate: maxDate?.getTime(),
      minDate: minDate?.getTime(),
      ...props,
    },
    handleChange,
    onCancel
  );
}

export const RangePicker = {
  show,
};

export type RangePickerProps = Omit<
  ShowProps,
  'value' | 'inputMode' | 'maxDate' | 'minDate' | 'start' | 'end'
> & {
  onChange: (newValue: Range) => void;
  onCancel?: () => void;
  value?: {
    start?: Date;
    end?: Date;
  };
  maxDate?: Date;
  minDate?: Date;
  inputMode?: 'text' | 'calendar';
};
