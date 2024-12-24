import NativeAlertDialog, { type ShowProps } from '../specs/NativeAlertDialog';

function show({
  onNegativePress,
  onPositivePress,
  onNeutralPress,
  onCancel,
  ...props
}: AlertDialogProps) {
  NativeAlertDialog.show(
    props,
    onPositivePress,
    onNegativePress,
    onNeutralPress,
    onCancel
  );
}

export const AlertDialog = {
  show,
};

export type AlertDialogProps = Omit<ShowProps, 'headerAlignment'> & {
  onPositivePress?: () => void;
  onNegativePress?: () => void;
  onNeutralPress?: () => void;
  onCancel?: () => void;
  headerAlignment?: 'start' | 'center';
};
