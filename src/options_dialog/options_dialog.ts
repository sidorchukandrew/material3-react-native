import NativeOptionsDialog, {
  type ShowProps,
} from '../specs/NativeOptionsDialog';

function show({
  onNegativePress,
  onPositivePress,
  onNeutralPress,
  onCancel,
  ...props
}: OptionsDialogProps) {
  validateSelections(props);

  NativeOptionsDialog.show(
    props,
    onPositivePress,
    onNegativePress,
    onNeutralPress,
    onCancel
  );
}

function validateSelections({ pickerType, selected }: OptionsDialogProps) {
  const length = selected?.length || 0;

  if (pickerType === 'singlechoice' && length > 1)
    console.warn(
      `Single choice picker can only have one selected item. Provided selected: ${selected}`
    );

  const isRowPicker = pickerType === 'row' || pickerType === undefined;
  if (isRowPicker && length > 0)
    console.warn(
      `Row picker can't have selections. Provided selected: ${selected}`
    );
}

export const OptionsDialog = {
  show,
};

export type OptionsDialogProps = Omit<
  ShowProps,
  'headerAlignment' | 'pickerType'
> & {
  onPositivePress?: (selectedIndexes?: number[]) => void;
  onNegativePress?: (selectedIndexes?: number[]) => void;
  onNeutralPress?: (selectedIndexes?: number[]) => void;
  onCancel?: (selectedIndexes?: number[]) => void;
  headerAlignment?: 'start' | 'center';
  pickerType?: 'row' | 'singlechoice' | 'multiselect';
};
